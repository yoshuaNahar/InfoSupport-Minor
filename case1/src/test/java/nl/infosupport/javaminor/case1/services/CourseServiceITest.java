package nl.infosupport.javaminor.case1.services;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
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
  private Course course2;

  @PersistenceContext
  private EntityManager em;

  @Before
  public void setup() {
    course = new Course();
    course.setTitle("Course Title");
    course.setCourseCode("Code");
    course.setDuration(10);

    course2 = new Course();
    course2.setTitle("Course Title");
    course2.setCourseCode("Code");
    course2.setDuration(10);
  }

  @Test
  public void getCourseByIdWithNullValueShouldReturnNull() {
    Course nullCourse = courseService.getCourseById(null);

    assertThat(nullCourse, is(nullValue()));
  }

  @Test
  public void getCourseByIdWithInvalidIdShouldReturnNull() {
    em.persist(course);
    em.flush();
    em.clear();

    Course nullCourse = courseService.getCourseById(2L);

    assertThat(nullCourse, is(nullValue()));
  }

  @Test
  public void getCourseByIdWithCorrectIdValueShouldReturnCorrectCourse() {
    em.persist(course);
    em.flush();
    em.clear();

    Course managedCourse = courseService.getCourseById(1L);

    assertCourse(managedCourse, course);
  }

  @Test
  public void getCourseByCourseCodeWithNullValueShouldReturnNull() {
    Course nullCourse = courseService.getCourseByCourseCode(null);

    assertThat(nullCourse, is(nullValue()));
  }

  @Test
  public void getCourseByCourseCodeWithEmptyStringShouldReturnNull() {
    em.persist(course);
    em.flush();
    em.clear();

    Course nullCourse = courseService.getCourseByCourseCode("");

    assertThat(nullCourse, is(nullValue()));
  }

  @Test
  public void getCourseByCourseCodeWithInvalidCourseCodeShouldReturnNull() {
    em.persist(course);
    em.flush();
    em.clear();

    Course nullCourse = courseService.getCourseByCourseCode("invalid course code");

    assertThat(nullCourse, is(nullValue()));
  }

  @Test
  public void getCourseByCourseCodeWithCorrectCourseCodeShouldReturnCorrectCourse() {
    em.persist(course);
    em.flush();
    em.clear();

    Course managedCourse = courseService.getCourseByCourseCode("Code");

    assertCourse(managedCourse, course);
  }

  @Test
  public void saveCourseWithNullValueShouldResultInNoCourseAdded() {
    courseService.saveCourse(null);
    em.flush();
    em.clear();

    Course nullCourse = courseService.getCourseById(1L);

    assertThat(nullCourse, is(nullValue()));
  }

  @Test(expected = PersistenceException.class)
  public void saveCourseWithEmptyCourseShouldResultInPersistenceException() {
    courseService.saveCourse(new Course()); // not null properties are null
    em.flush();
    em.clear();
  }

  @Test
  public void saveCourseWithCorrectCourseShouldResultInOneCoursePersisted() {
    courseService.saveCourse(course);
    em.flush();
    em.clear();

    Course managedCourse = courseService.getCourseById(1L);

    assertCourse(managedCourse, course);
  }

  @Test
  public void saveCourseWithDuplicateCoursesShouldResultInOneCoursePersisted() {
    courseService.saveCourse(course);
    em.flush();
    courseService.saveCourse(course2);
    em.flush();
    em.clear();

    Course managedCourse = courseService.getCourseById(1L);
    Course nullCourse = courseService.getCourseById(2L);

    assertCourse(managedCourse, course);
    assertThat(nullCourse, is(nullValue()));
  }

  @Test
  public void saveCourseAndCourseInstancesWithCourseAndCourseInstanceShouldResultInOneCourseAndOneCourseInstancePersisted() {
    CourseInstance courseInstance = new CourseInstance();
    courseInstance.setStartDate(LocalDate.now());
    courseInstance.setCourse(course);
    course.getCourseInstances().add(courseInstance);

    courseService.saveCoursesAndCourseInstances(Arrays.asList(course));
    em.flush();
    em.clear();

    Course managedCourse = courseService.getCourseById(1L);

    assertCourse(managedCourse, course);
    assertThat(managedCourse.getCourseInstances().size(), is(1)); // 1 course instance
  }

  @Test
  public void saveCoursesAndCourseInstancesWithDuplicateCoursesAndUniqueCourseInstancesShouldResultInOneCourseAndTwoCourseInstancesPersisted() {
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

    assertCourse(managedCourses.get(0), course);
    assertThat(managedCourses.size(), is(1)); // 1 course
    assertThat(managedCourses.get(0).getCourseInstances().size(), is(2)); // 2 course instances
  }

  @Test
  public void saveCoursesAndCourseInstancesWithFromDateInThePastShouldResultInNoCoursePersist() {
    CourseInstance courseInstance = new CourseInstance();
    courseInstance.setStartDate(LocalDate.now());
    courseInstance.setCourse(course);
    course.getCourseInstances().add(courseInstance);

    LocalDate yesterday = LocalDate.now().minus(1, ChronoUnit.DAYS);

    courseService.saveCoursesAndCourseInstances(Arrays.asList(course), yesterday, yesterday);
    em.flush();
    em.clear();

    Course managedCourse = em.find(Course.class, 1L);

    assertCourse(managedCourse, course);
  }

  @Test
  public void saveCoursesAndCourseInstancesWithCorrectDateShouldResultInCoursePersist() {
    CourseInstance courseInstance = new CourseInstance();
    courseInstance.setStartDate(LocalDate.now());
    courseInstance.setCourse(course);
    course.getCourseInstances().add(courseInstance);

    LocalDate tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS);

    courseService.saveCoursesAndCourseInstances(Arrays.asList(course), tomorrow, tomorrow);
    em.flush();
    em.clear();

    Course managedCourse = em.find(Course.class, 1L);

    assertCourse(managedCourse, course);
  }

  private void assertCourse(Course managedCourse, Course course) {
    assertThat(managedCourse.getTitle(), is(course.getTitle()));
    assertThat(managedCourse.getCourseCode(), is(course.getCourseCode()));
    assertThat(managedCourse.getDuration(), is(course.getDuration()));
  }

}
