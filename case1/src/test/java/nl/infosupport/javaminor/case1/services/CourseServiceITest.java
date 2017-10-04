package nl.infosupport.javaminor.case1.services;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nl.infosupport.javaminor.case1.config.TestConfig;
import nl.infosupport.javaminor.case1.entities.Course;
import nl.infosupport.javaminor.case1.entities.CourseInstance;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(classes = TestConfig.class)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
// Recreate database after each test
// https://www.petrikainulainen.net/programming/spring-framework/spring-from-the-trenches-resetting-auto-increment-columns-before-each-test-method/
public class CourseServiceITest extends AbstractTransactionalJUnit4SpringContextTests {

  @Autowired
  private CourseService courseService;

  private Course course;

  @PersistenceContext
  private EntityManager em;

  @Before
  public void setup() {
    course = new Course();
    course.setTitle("Course Title");
    course.setCourseCode("Code");
    course.setDuration(10);
  }

  @Test
  public void saveCourseShouldResultInOneCoursePersisted() {
    courseService.saveCourse(course);
    em.flush();
    em.clear();
    Course managedCourse = courseService.getCourseById(1L);

    assertCourse(managedCourse, course);
  }

  @Test
  public void saveCourseDuplicateCoursesShouldResultInOneCoursePersisted() {
    Course course2 = new Course();
    course2.setTitle("Course Title");
    course2.setCourseCode("Code");
    course2.setDuration(10);

    courseService.saveCourse(course);
    em.flush();
    courseService.saveCourse(course2);
    em.flush();
    em.clear();
    List<Course> courses = courseService.getCourses();
    System.out.println(courses);

    assertCourse(courses.get(0), course);
    assertThat(courses.size(), is(1));
  }

  @Test
  public void saveCoursesAndCourseInstancesOneCourseWithOneCourseInstanceShouldResultInOneCourseAndOneCourseInstancePersisted() {
    CourseInstance courseInstance = new CourseInstance();
    courseInstance.setStartDate(LocalDate.now());
    courseInstance.setCourse(course);
    course.getCourseInstances().add(courseInstance);

    courseService.saveCoursesAndCourseInstances(Collections.singletonList(course));
    em.flush();
    em.clear();

    Course managedCourse = courseService.getCourseById(1L);

    assertCourse(managedCourse, course);
    assertThat(managedCourse.getCourseInstances().size(), is(1));
  }

  @Test
  public void saveCoursesAndCourseInstancesDuplicateCourseWithUniqueCourseInstancesShouldResultInOneCourseAndTwoCourseInstancePersisted() {
    Course course2 = new Course();
    course2.setTitle("Course Title");
    course2.setCourseCode("Code");
    course2.setDuration(10);

    CourseInstance courseInstance1 = new CourseInstance();
    courseInstance1.setStartDate(LocalDate.now());
    courseInstance1.setCourse(course);
    course.getCourseInstances().add(courseInstance1);

    CourseInstance courseInstance2 = new CourseInstance();
    courseInstance2.setStartDate(LocalDate.now().plus(1, ChronoUnit.DAYS));
    courseInstance2.setCourse(course2);
    course2.getCourseInstances().add(courseInstance2);

    List<Course> courses = new ArrayList<>();
    courses.add(course);
    courses.add(course2);

    courseService.saveCoursesAndCourseInstances(courses);
    em.flush();

    em.clear();
    List<Course> managedCourses = courseService.getCourses();
    System.out.println(managedCourses);

    assertCourse(managedCourses.get(0), course);
    assertThat(managedCourses.size(), is(1));
  }

  private void assertCourse(Course managedCourse, Course course) {
    assertThat(managedCourse.getTitle(), is(course.getTitle()));
    assertThat(managedCourse.getCourseCode(), is(course.getCourseCode()));
    assertThat(managedCourse.getDuration(), is(course.getDuration()));
  }

}
