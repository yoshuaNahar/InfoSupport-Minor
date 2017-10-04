package nl.infosupport.javaminor.case1.daos;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nl.infosupport.javaminor.case1.entities.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StudentDao {

  private static final Logger LOG = LoggerFactory.getLogger(StudentDao.class);

  @PersistenceContext
  private EntityManager em;

  @Transactional(readOnly = true)
  public List<Student> getStudents() {
    return em.createQuery("SELECT s FROM Student s", Student.class)
        .getResultList();
  }

  @Transactional(readOnly = true)
  public Student getStudentByFirstNameAndLastName(String firstName, String lastName) {
    List<Student> students = em.createQuery("SELECT s FROM Student s "
        + "WHERE s.firstName = :firstName AND s.lastName = :lastName", Student.class)
        .getResultList();

    return students.isEmpty() ? null : students.get(0);
  }

  public Student saveStudent(Student student) {
    em.persist(student);

    return student;
  }

}
