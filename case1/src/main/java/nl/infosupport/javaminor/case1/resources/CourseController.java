package nl.infosupport.javaminor.case1.resources;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import nl.infosupport.javaminor.case1.entities.Course;
import nl.infosupport.javaminor.case1.entities.view.PersistedStatistics;
import nl.infosupport.javaminor.case1.services.CourseService;
import nl.infosupport.javaminor.case1.util.ByteToStringConverter;
import nl.infosupport.javaminor.case1.util.StringToCoursesConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CourseController {

  private static final Logger LOG = LoggerFactory.getLogger(CourseController.class);

  private CourseService courseService;

  private ByteToStringConverter byteToStringConverter;
  private StringToCoursesConverter stringToCoursesConverter;

  @Autowired
  public CourseController(
      CourseService courseService,
      ByteToStringConverter byteToStringConverter,
      StringToCoursesConverter stringToCoursesConverter) {
    this.courseService = courseService;
    this.byteToStringConverter = byteToStringConverter;
    this.stringToCoursesConverter = stringToCoursesConverter;
  }

  @GetMapping("/cursussen/toevoegen")
  public String getAddCoursesPage() {
    return "addCourses";
  }

  @PostMapping("/cursussen/toevoegen")
  public String postFileUpload(@RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes,
      @RequestParam("from") @DateTimeFormat(iso = ISO.DATE) LocalDate from,
      @RequestParam("to") @DateTimeFormat(iso = ISO.DATE) LocalDate to) {
    byte[] rawCourses;

    System.out.println(from);
    System.out.println(to);

    try {
      rawCourses = file.getBytes();
    } catch (IOException e) {
      e.printStackTrace();
      return "error";
    }

    String stringCourses = byteToStringConverter.convertByteArrayToString(rawCourses);
    List<Course> courses = stringToCoursesConverter.convertToCourses(stringCourses);

    PersistedStatistics persistedStatistics = courseService
        .saveCoursesAndCourseInstances(courses, from, to);

    redirectAttributes.addFlashAttribute("persistedStatistics", persistedStatistics);

    return "redirect:/cursusoverzicht";
  }

}
