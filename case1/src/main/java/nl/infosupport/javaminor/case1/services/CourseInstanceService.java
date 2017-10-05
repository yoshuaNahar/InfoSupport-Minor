package nl.infosupport.javaminor.case1.services;

import java.util.List;
import nl.infosupport.javaminor.case1.daos.CourseInstanceDao;
import nl.infosupport.javaminor.case1.entities.CourseInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseInstanceService {

  private static final Logger LOG = LoggerFactory.getLogger(CourseService.class);

  private CourseInstanceDao courseInstanceDao;

  @Autowired
  public CourseInstanceService(
      CourseInstanceDao courseInstanceDao) {
    this.courseInstanceDao = courseInstanceDao;
  }

  // The courseInstanceDao.getCourseInstanceById() also gets all students
  public CourseInstance getCourseInstanceById(Long courseInstanceId) {
    return courseInstanceDao.getCourseInstanceById(courseInstanceId);
  }

  // The courseInstanceDao.getCoursesInstances() also gets all students
  public List<CourseInstance> getCoursesInstances() {
    return courseInstanceDao.getCoursesInstances();
  }

}
