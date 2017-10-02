package nl.infosupport.javaminor.case1.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nl.infosupport.javaminor.case1.entities.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StudentDao {

  @PersistenceContext
  private EntityManager em;

  public Student save(Student student) {
    em.persist(student);

    return student;
  }

  public Student getById(Long id) {
    return em.find(Student.class, id);
  }

  public Student merge(Student student) {
    return em.merge(student);
  }

}
