package nl.yoboid.vehicle.controllers;

import nl.yoboid.vehicle.repositories.VehicleRepository;
import nl.yoboid.domain.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class VehicleController {

  private static final String BASE = "/vehicles";

  private final VehicleRepository repository;

  @Autowired
  public VehicleController(VehicleRepository repository) {
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
  public ResponseEntity save(@RequestBody Vehicle vehicle) {
    return Optional.ofNullable(repository.save(vehicle))
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.badRequest().build());
  }

}
