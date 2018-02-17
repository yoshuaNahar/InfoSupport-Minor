package nl.yoboid.maintenance.repositories;

import nl.yoboid.domain.entities.Maintenance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MaintenanceRepository extends CrudRepository<Maintenance, Long> {

}
