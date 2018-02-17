package nl.yoboid.customer.controllers;

import java.util.Optional;
import nl.yoboid.customer.repositories.CustomerRepository;
import nl.yoboid.domain.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

  private static final String BASE = "/customers";

  private final CustomerRepository repository;

  @Autowired
  public CustomerController(CustomerRepository repository) {
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
  public ResponseEntity save(@RequestBody Customer customer) {
    return Optional.ofNullable(repository.save(customer))
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.badRequest().build());
  }
}
