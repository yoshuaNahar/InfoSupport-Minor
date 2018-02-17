package nl.yoboid.maintenance.controllers;

import nl.yoboid.domain.entities.Maintenance;
import nl.yoboid.domain.entities.VehicleMaintenanceWrapper;
import nl.yoboid.maintenance.events.senders.ApkCheckFinishedSender;
import nl.yoboid.maintenance.repositories.MaintenanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MaintenanceController {

  private static final String BASE = "/maintenances";

  private static final Logger logger = LoggerFactory.getLogger(MaintenanceController.class);

  private final MaintenanceRepository repository;
  private final ApkCheckFinishedSender sender;

  @Autowired
  public MaintenanceController(ApkCheckFinishedSender sender, MaintenanceRepository repository) {
    this.sender = sender;
    this.repository = repository;
  }

  @GetMapping(BASE)
  public ResponseEntity getAll() {
    return Optional.ofNullable(repository.findAll())
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping(BASE + "/{id}")
  public ResponseEntity getOneById(@PathVariable long id) {
    return Optional.ofNullable(repository.findOne(id))
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping(BASE)
  public ResponseEntity save(@RequestBody Maintenance maintenance) {
    return Optional.ofNullable(repository.save(maintenance))
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.badRequest().build());
  }

  @PostMapping(BASE + "/state")
  public Maintenance updateState(@RequestBody VehicleMaintenanceWrapper vehicleMaintenanceWrapper) {
    logger.info("updateState {}", vehicleMaintenanceWrapper);
    Maintenance maintenance = vehicleMaintenanceWrapper.getMaintenance();
    repository.save(vehicleMaintenanceWrapper.getMaintenance());
    if (maintenance.getState() == Maintenance.State.RDW_CHECK) {
      try {
        sender.send(vehicleMaintenanceWrapper.getVehicle());
      } catch (Exception exception) {
        throw new IllegalStateException(exception);
      }
    }
    return maintenance;
  }
}
