package nl.infosupport.minor.case1_backend.repository;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import nl.infosupport.minor.case1_domain.Cursus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CursusRepositoryTest {

  @Autowired
  private CursusRepository cursusRepository;

  @Autowired
  private TestEntityManager em;

  @Test
  public void findCursusesExpectCursuses() {
    em.persist(new Cursus("Cursus 1", "sd", "4 uur", "2017-08-01"));
    em.flush();
    em.clear();

    List<Cursus> cursus = cursusRepository.findAll();

    assertThat(cursus.get(0).getTitle(), is("Cursus 1"));
  }

}
