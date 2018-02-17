package nl.yoboid.web.services;

import nl.yoboid.domain.entities.Maintenance;
import nl.yoboid.domain.entities.Vehicle;
import nl.yoboid.domain.entities.VehicleMaintenanceWrapper;
import nl.yoboid.web.interfaces.MaintenanceService;
import nl.yoboid.web.viewentity.MaintenanceViewEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

  private static final Logger logger = LoggerFactory.getLogger(MaintenanceServiceImpl.class);

  @Value("${service.url.maintenance}")
  private String maintenanceURL;

  @Value("${service.url.vehicle}")
  private String vehicleURL;

  private final RestTemplate template;

  @Autowired
  public MaintenanceServiceImpl(RestTemplate template) {
    this.template = template;
  }

  @Override
  public List<MaintenanceViewEntity> getMaintenanceViews() {
    ResponseEntity<Maintenance[]> maintenanceResponse = template.getForEntity(maintenanceURL,
      Maintenance[].class);

    if (maintenanceResponse.getStatusCode() != HttpStatus.OK) {
      return new ArrayList<>();
    }

    List<Maintenance> maintenances = Arrays.asList(maintenanceResponse.getBody());
    logger.info("Maintenances {}", maintenances);

    List<MaintenanceViewEntity> maintenanceViewEntities = new ArrayList<>();
    for (Maintenance maintenance : maintenances) {
      ResponseEntity<Vehicle> vehicleResponse = template.getForEntity(
        vehicleURL + "/" + maintenance.getVehicleId(),
        Vehicle.class);

      if (vehicleResponse.getStatusCode() != HttpStatus.OK) {
        return new ArrayList<>();
      }

      Vehicle vehicle = vehicleResponse.getBody();
      logger.info("Vehicle: {}", vehicle);

      MaintenanceViewEntity maintenanceViewEntity = new MaintenanceViewEntity(
        maintenance.getId(),
        maintenance.getDate(),
        vehicle.getNumberPlate(),
        maintenance.getState());

      maintenanceViewEntities.add(maintenanceViewEntity);
    }

    return maintenanceViewEntities;
  }

  @Override
  public void changeStateOfMaintenance(Maintenance maintenance) {
    ResponseEntity<Maintenance> maintenanceResponseEntity = template.getForEntity(
      maintenanceURL + "/" + maintenance.getId(),
      Maintenance.class);

    ResponseEntity<Vehicle> vehicle = template.getForEntity(
      vehicleURL + "/" + maintenanceResponseEntity.getBody().getVehicleId(),
      Vehicle.class);

    VehicleMaintenanceWrapper vehicleMaintenanceWrapper = new VehicleMaintenanceWrapper(
      vehicle.getBody(),
      maintenanceResponseEntity.getBody());

    logger.info("Maintenance: {}", maintenanceResponseEntity.getBody());

    ResponseEntity entity = template.postForEntity(
      maintenanceURL + "/state",
      vehicleMaintenanceWrapper,
      Maintenance.class);

    if (!entity.hasBody()) {
      throw new HttpClientErrorException(entity.getStatusCode());
    }
  }

  @Override
  public Maintenance getMaintenanceById(long id) {
    ResponseEntity<Maintenance> maintenanceResponseEntity = template.getForEntity(
      maintenanceURL + "/" + id,
      Maintenance.class);

    Maintenance maintenance = maintenanceResponseEntity.getBody();

    logger.info("Maintenance: {}", maintenance);

    return maintenance;
  }

  @Override
  public Maintenance addMaintenance(Maintenance maintenance) {
    return template.postForObject(maintenanceURL, maintenance, Maintenance.class);
  }

}
