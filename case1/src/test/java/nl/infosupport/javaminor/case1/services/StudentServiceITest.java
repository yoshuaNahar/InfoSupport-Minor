package nl.infosupport.javaminor.case1.services;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nl.infosupport.javaminor.case1.config.TestConfig;
import nl.infosupport.javaminor.case1.entities.Course;
import nl.infosupport.javaminor.case1.entities.CourseInstance;
import nl.infosupport.javaminor.case1.entities.Student;
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
public class StudentServiceITest extends AbstractTransactionalJUnit4SpringContextTests {

  @Autowired
  private StudentService studentService;

  private Student student;
  private Course course;
  private CourseInstance courseInstance;

  @PersistenceContext
  private EntityManager em;

  @Before
  public void setup() {
    student = new Student();
    student.setFirstName("Jason");
    student.setLastName("Phillips");

    course = new Course();
    course.setTitle("Course Title");
    course.setCourseCode("Code");
    course.setDuration(10);

    courseInstance = new CourseInstance();
    courseInstance.setStartDate(LocalDate.now());
    courseInstance.setCourse(course);

    course.getCourseInstances().add(courseInstance);
  }

  @Test
  public void addStudentShouldResultIn1StudentPersisted() {
    studentService.saveStudent(student);
    em.flush();
    em.clear();

    Student managedStudent = studentService.getStudentById(1L);

    assertStudent(managedStudent, student);
  }

  @Test
  public void addCourseInstanceToStudentShouldResultInStudentWithCourseInstance() {
    studentService.saveStudent(student);
    em.persist(course);
    em.persist(courseInstance);
    em.flush();

    student.getCourseInstances().add(courseInstance);
    // studentService.addCourseInstance(student); // you don't have to do this here, because it is the same transaction
    em.flush();
    em.clear();

    Student managedStudent = studentService.getStudentById(1L);
    CourseInstance managedCourseInstance = managedStudent.getCourseInstances().get(0);

    assertThat(managedCourseInstance.getStartDate(), is(courseInstance.getStartDate()));
  }

  private void assertStudent(Student managedStudent, Student student) {
    assertThat(managedStudent.getFirstName(), is(student.getFirstName()));
    assertThat(managedStudent.getLastName(), is(student.getLastName()));
  }

}
