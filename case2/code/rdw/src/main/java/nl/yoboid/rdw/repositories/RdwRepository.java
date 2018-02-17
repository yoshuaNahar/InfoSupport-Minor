package nl.yoboid.rdw.repositories;

import nl.yoboid.domain.entities.RdwRequestObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RdwRepository extends CrudRepository<RdwRequestObject, Long> {

  RdwRequestObject findByNumberPlate(String numberPlate);

}
