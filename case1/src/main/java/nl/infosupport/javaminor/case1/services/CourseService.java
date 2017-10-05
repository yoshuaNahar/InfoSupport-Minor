package nl.infosupport.javaminor.case1.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import nl.infosupport.javaminor.case1.daos.CourseDao;
import nl.infosupport.javaminor.case1.daos.CourseInstanceDao;
import nl.infosupport.javaminor.case1.entities.Course;
import nl.infosupport.javaminor.case1.entities.CourseInstance;
import nl.infosupport.javaminor.case1.entities.view.PersistedStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

  private static final Logger LOG = LoggerFactory.getLogger(CourseService.class);

  private CourseDao courseDao;
  private CourseInstanceDao courseInstanceDao;

  @Autowired
  public CourseService(CourseDao courseDao, CourseInstanceDao courseInstanceDao) {
    this.courseDao = courseDao;
    this.courseInstanceDao = courseInstanceDao;
  }

  /**
   * Returns a <code>Course</code> based on the <code>id</code>.
   *
   * @param courseId primary key
   * @return <code>Course</code> with matching id, otherwise null.
   */
  Course getCourseById(Long courseId) {
    if (courseId == null) {
      return null;
    }

    return courseDao.getCourseById(courseId);
  }

  /**
   * Returns a <code>Course</code> based on the <code>courseCode</code>.
   *
   * @param courseCode Course code <code>String</code>
   * @return <code>Course</code> with matching <code>courseCode</code>, otherwise null.
   */
  public Course getCourseByCourseCode(String courseCode) {
    return courseDao.getCourseByCourseCode(courseCode);
  }

  /**
   * Returns all <code>Course</code>s in the db.
   *
   * @return List of all persisted <code>Course</code>s
   */
  public List<Course> getCourses() {
    return courseDao.getCourses();
  }

  /**
   * Make <code>Course</code> managed and persistent.
   *
   * @param course course to persist
   * @return the persisted <code>Course</code>
   */
  public void saveCourse(Course course) {
    if (course != null) {
      // if course doesn't already exist in db, save course to db
      if (getCourseByCourseCode(course.getCourseCode()) == null) {
        courseDao.saveCourse(course);
      }
    }
  }

  /**
   *
   * @param courses
   * @return
   */
  public PersistedStatistics saveCoursesAndCourseInstances(List<Course> courses) {
    // Not using CascadeType.PERSIST on the courseInstances inside course.
    // So this is done manually
    int persistedCourses = 0;
    int persistedCoursesInstances = 0;

    for (Course course : courses) {
      saveCourse(course);
      if (course.getId() != null) {
        persistedCourses++;
      }
      for (CourseInstance courseInstance : course.getCourseInstances()) {
        saveCourseInstanceIfUnique(courseInstance);
        if (courseInstance.getId() != null) {
          persistedCoursesInstances++;
        }
      }
    }

    return new PersistedStatistics(persistedCourses, persistedCoursesInstances);
  }

  public PersistedStatistics saveCoursesAndCourseInstances(List<Course> courses, LocalDate from,
      LocalDate to) {
    if (from != null && to != null) {
      checkWhichCourseInstancesAreWithinDates(courses, from, to);
    }

    return saveCoursesAndCourseInstances(courses);
  }

  private void saveCourseInstanceIfUnique(CourseInstance courseInstance) {
    if (courseInstanceDao
        .getCourseInstanceByCourseIdAndStartDate(courseInstance.getCourse().getCourseCode(),
            courseInstance.getStartDate()) == null) {
      Course managedCourse = courseDao
          .getCourseByCourseCode(courseInstance.getCourse().getCourseCode());
      courseInstance.setCourse(managedCourse);
      courseInstanceDao.saveCourseInstance(courseInstance);
    }
  }

  private List<Course> checkWhichCourseInstancesAreWithinDates(List<Course> courses, LocalDate from,
      LocalDate to) {
    // every course that enters here has 1 courseinstance
    for (Course course : courses) {
      CourseInstance courseInstance = course.getCourseInstances().get(0);
      LocalDate courseInstanceStart = courseInstance.getStartDate();
      LocalDate courseInstanceEnd = courseInstanceStart.plus(course.getDuration(), ChronoUnit.DAYS);
      if (courseInstanceStart.isBefore(from) || courseInstanceEnd.isAfter(to)) {
        course.getCourseInstances().clear(); // remove courseInstance insert
      }
    }

    return courses;
  }

}
