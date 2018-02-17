package nl.yoboid.rdw.controllers;

import nl.yoboid.domain.entities.RdwRequestObject;
import nl.yoboid.rdw.repositories.RdwRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RdwController {

  private static final String BASE = "/rdw";

  private final RdwRepository repository;

  @Autowired
  public RdwController(RdwRepository repository) {
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

  @GetMapping(BASE + "/number-plate/{numberPlate}")
  public ResponseEntity getOneById(@PathVariable String numberPlate) {
    return Optional.ofNullable(repository.findByNumberPlate(numberPlate))
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping(BASE)
  public ResponseEntity save(@RequestBody RdwRequestObject rdw) {
    return Optional.ofNullable(repository.save(rdw))
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.badRequest().build());
  }
}
