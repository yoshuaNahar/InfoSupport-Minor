package nl.infosupport.javaminor.case1.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import nl.infosupport.javaminor.case1.entities.Course;
import nl.infosupport.javaminor.case1.entities.CourseInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StringToCourseListConverter {

  private static final Logger LOG = LoggerFactory.getLogger(StringToCourseListConverter.class);

  public List<Course> convertToCourses(String stringCourses) {
    List<Course> courses = new ArrayList<>();

    if (stringCourses == null || stringCourses.trim().isEmpty()) {
      LOG.debug("stringCourses is null or empty");
      return courses;
    }

    List<String> stringCoursesList = splitStringByCourse(stringCourses);

    for (String courseGroup : stringCoursesList) {
      courses.add(mapStringToCourse(courseGroup));
    }

    return courses;
  }

  // Linebreak matcher
  // \R	Any Unicode linebreak sequence
  private List<String> splitStringByCourse(String stringCourses) {
    List<String> stringCoursesList = Arrays.asList(stringCourses.split("\\R\\R"));
    LOG.debug("stringCoures split into {}, stringCoursesList: {}",
        stringCoursesList.size(), stringCoursesList);

    return stringCoursesList;
  }

  private Course mapStringToCourse(String courseGroup) {
    String title = "Titel";
    String courseCode = "Cursuscode";
    String duration = "Duur";
    String startDate = "Startdatum";

    Course course = new Course();

    String[] coursePart = courseGroup.split("(: |\\R)");

    if (coursePart[0].equals(title)) {
      course.setTitle(coursePart[1]);
    }
    if (coursePart[2].equals(courseCode)) {
      course.setCourseCode(coursePart[3]);
    }
    if (coursePart[4].equals(duration)) {
      course.setDuration(parseDurationStringOf(coursePart[5]));
    }
    if (coursePart[6].equals(startDate)) {
      CourseInstance courseInstance = new CourseInstance();
      courseInstance.setStartDate(parseStartDateStringOf(coursePart[7]));
      courseInstance.setCourse(course);

      List<CourseInstance> courseInstances = new ArrayList<>(
          Collections.singletonList(courseInstance));
      course.setCourseInstances(courseInstances);
    }

    return course;
  }

  private int parseDurationStringOf(String stringInt) {
    String days = "dagen";
    String daysString = stringInt.substring(0, 1);
    LOG.debug("Duration of this course: {}", daysString);

    return stringInt.substring(2, 7).equals(days) ? Integer.parseInt(daysString) : 0;
  }

  private LocalDate parseStartDateStringOf(String stringDate) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter
        .ofPattern("dd/MM/yyyy", Locale.US);

    return LocalDate.parse(stringDate, dateTimeFormatter);
  }

}
