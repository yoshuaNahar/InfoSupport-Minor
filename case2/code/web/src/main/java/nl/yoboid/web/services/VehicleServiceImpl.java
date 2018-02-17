package nl.yoboid.web.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.yoboid.domain.entities.Vehicle;
import nl.yoboid.web.interfaces.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class VehicleServiceImpl implements VehicleService {

  @Value("${service.url.vehicle}")
  private String url;

  private final RestTemplate template;
  private final ObjectMapper mapper;

  @Autowired
  public VehicleServiceImpl(RestTemplate template, ObjectMapper mapper) {
    this.template = template;
    this.mapper = mapper;
  }

  @Override
  public Vehicle addVehicle(Vehicle vehicle) {
    String json;
    try {
      json = mapper.writeValueAsString(vehicle);
    }
    catch (JsonProcessingException e) {
      throw new IllegalArgumentException(e);
    }

    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);
    HttpEntity<String> request = new HttpEntity<>(json, headers);

    return template.postForObject(url, request, Vehicle.class);
  }

  @Override
  public Vehicle getVehicleById(long vehicleId) {
    return template.getForObject(url + "/" + vehicleId, Vehicle.class);
  }
}
