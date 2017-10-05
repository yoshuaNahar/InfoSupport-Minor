package nl.infosupport.javaminor.case1.util;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.List;
import nl.infosupport.javaminor.case1.entities.Course;
import org.junit.Test;

public class StringToCoursesConverterUTest {

  private StringToCoursesConverter textToCoursesService = new StringToCoursesConverter();

  private String correctCourseData = "Titel: C# Programmeren\n"
      + "Cursuscode: CNETIN\n"
      + "Duur: 5 dagen\n"
      + "Startdatum: 13/10/2014\n"
      + "\n"
      + "Titel: C# Programmeren\n"
      + "Cursuscode: CNETIN\n"
      + "Duur: 5 dagen\n"
      + "Startdatum: 20/10/2014\n";

  @Test
  public void convertToCoursesWithNullStringShouldReturn0Courses() {
    List<Course> courses = textToCoursesService.convertToCourses(null);

    assertThat(courses.size(), is(0));
  }

  @Test
  public void convertToCoursesWithEmptyStringShouldReturn0Courses() {
    List<Course> courses = textToCoursesService.convertToCourses("");

    assertThat(courses.size(), is(0));
  }

  @Test
  public void splitTextByCourseWithCorrectStringShouldReturn2Groups() {
    List<Course> courses = textToCoursesService.convertToCourses(correctCourseData);

    assertThat(courses.size(), is(2));
  }

  @Test
  public void mapStringToCourseGetFirstCourseWithCorrectStringShouldReturnFirstCourse() {
    List<Course> courses = textToCoursesService.convertToCourses(correctCourseData);
    Course course = courses.get(0);

    assertThat(course.getTitle(), is("C# Programmeren"));
    assertThat(course.getCourseCode(), is("CNETIN"));
    assertThat(course.getDuration(), is(5));
    assertThat(course.getCourseInstances().get(0).getStartDate(), is(LocalDate.of(2014, 10, 13)));
  }

  @Test
  public void mapStringToCourseGetSecondCourseWithCorrectStringShouldReturnSecondCourse() {
    // NOTE: second course only has a different date!
    List<Course> courses = textToCoursesService.convertToCourses(correctCourseData);
    Course course = courses.get(1);

    assertThat(course.getCourseInstances().get(0).getStartDate(), is(LocalDate.of(2014, 10, 20)));
  }

}
