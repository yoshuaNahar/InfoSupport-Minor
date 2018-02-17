package nl.infosupport.minor.case1_backend.service;

import java.util.List;
import nl.infosupport.minor.case1_backend.repository.CursusRepository;
import nl.infosupport.minor.case1_domain.Cursus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursusService {

  @Autowired
  private CursusRepository cursusRepository;

  public List<Cursus> findCursusen() {
    return cursusRepository.findAll();
  }

  public void add(Cursus cursus) {
    cursusRepository.save(cursus);
  }

}
