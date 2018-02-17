package nl.yoboid.vehicle.repositories;

import nl.yoboid.domain.entities.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
}
