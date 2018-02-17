package nl.infosupport.minor.case1_backend.repository;

import nl.infosupport.minor.case1_domain.Cursus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursusRepository extends JpaRepository<Cursus, Integer> {

}
