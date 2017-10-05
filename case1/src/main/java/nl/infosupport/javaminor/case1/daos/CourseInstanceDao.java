package nl.infosupport.javaminor.case1.daos;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nl.infosupport.javaminor.case1.entities.CourseInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CourseInstanceDao {

  private static final Logger LOG = LoggerFactory.getLogger(CourseInstanceDao.class);

  @PersistenceContext
  private EntityManager em;

  @Transactional(readOnly = true)
  public CourseInstance getCourseInstanceById(Long courseInstanceId) {
    CourseInstance courseInstance = em.find(CourseInstance.class, courseInstanceId);
    courseInstance.getStudents().size();

    return courseInstance;
  }

  @Transactional(readOnly = true)
  public CourseInstance getCourseInstanceByCourseIdAndStartDate(String courseCode,
      LocalDate startDate) {
    List<CourseInstance> courseInstances = em
        .createQuery("SELECT c FROM CourseInstance c"
                + " WHERE c.course.courseCode = :courseCode AND c.startDate = :startDate",
            CourseInstance.class)
        .setParameter("courseCode", courseCode)
        .setParameter("startDate", startDate)
        .getResultList();

    return courseInstances.isEmpty() ? null : courseInstances.get(0);
  }

  @Transactional(readOnly = true)
  public List<CourseInstance> getCoursesInstances() {
    List<CourseInstance> courseInstances = em
        .createQuery("SELECT c FROM CourseInstance c", CourseInstance.class)
        .getResultList();

    // initialize the lazy colleciton
    // https://stackoverflow.com/questions/15359306/how-to-load-lazy-fetched-items-from-hibernate-jpa-in-my-controller
    courseInstances.forEach(courseInstance -> courseInstance.getStudents().size());

    return courseInstances;
  }

  public CourseInstance saveCourseInstance(CourseInstance courseInstance) {
    em.persist(courseInstance);

    return courseInstance;
  }

}
