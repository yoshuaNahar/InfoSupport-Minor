package nl.infosupport.javaminor.case1.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nl.infosupport.javaminor.case1.entities.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CourseDao {

  private static final Logger LOG = LoggerFactory.getLogger(CourseDao.class);

  @PersistenceContext
  private EntityManager em;

  @Transactional(readOnly = true)
  public Course getCourseById(Long id) {
    return em.find(Course.class, id);
  }

  @Transactional(readOnly = true)
  public Course getCourseByCourseCode(String courseCode) {
    List<Course> courseList = em
        .createQuery("SELECT c FROM Course c WHERE c.courseCode = :courseCode", Course.class)
        .setParameter("courseCode", courseCode)
        .getResultList();

    return courseList.isEmpty() ? null : courseList.get(0);
  }

  @Transactional(readOnly = true)
  public List<Course> getCourses() {
    return em
        .createQuery("SELECT c FROM Course c", Course.class)
        .getResultList();
  }

  /*
  https://stackoverflow.com/questions/19973277/hibernate-how-to-catch-integrity-constraint-violation-unique-constraint-or-i
  This is happening, the Rollback exception is called in the proxy, because the actual writing
  to the db happens in the proxy. So you will never handle the exception in here
   */
  public Course saveCourse(Course course) {
    em.persist(course);

    return course;
  }

}
