package nl.infosupport.javaminor.case1.services;

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

  public Course getCourseById(Long courseId) {
    return courseDao.getCourseById(courseId);
  }

  public List<Course> getCourses() {
    return courseDao.getCourses();
  }

  public void saveCourse(Course course) {
    if (courseDao.getCourseByCourseCode(course.getCourseCode()) == null) {
      courseDao.saveCourse(course);
    }
  }

  // Because I am not using propogation
  public PersistedStatistics saveCoursesAndCourseInstances(List<Course> courses) {
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

  private void saveCourseInstanceIfUnique(CourseInstance courseInstance) {
    if (courseInstanceDao.getCourseInstanceByCourseIdAndStartDate(courseInstance.getCourse().getCourseCode(),
        courseInstance.getStartDate()) == null) {
      Course managedCourse = courseDao.getCourseByCourseCode(courseInstance.getCourse().getCourseCode());
      courseInstance.setCourse(managedCourse);
      courseInstanceDao.saveCourseInstance(courseInstance);
    }
  }

}
