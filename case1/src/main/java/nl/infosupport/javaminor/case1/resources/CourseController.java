package nl.infosupport.javaminor.case1.resources;

import java.io.IOException;
import java.util.List;
import nl.infosupport.javaminor.case1.entities.Course;
import nl.infosupport.javaminor.case1.entities.view.PersistedStatistics;
import nl.infosupport.javaminor.case1.services.CourseService;
import nl.infosupport.javaminor.case1.util.ByteToStringConverter;
import nl.infosupport.javaminor.case1.util.StringToCourseListConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
  private StringToCourseListConverter stringToCourseListConverter;

  @Autowired
  public CourseController(
      CourseService courseService,
      ByteToStringConverter byteToStringConverter,
      StringToCourseListConverter stringToCourseListConverter) {
    this.courseService = courseService;
    this.byteToStringConverter = byteToStringConverter;
    this.stringToCourseListConverter = stringToCourseListConverter;
  }

  //@GetMapping(value = {"/", "/cursussen"})
//  public String get(Model model) {
//    List<Course> courses = courseService.getCourses();
//    model.addAttribute("courses", courses);
//
//    return "viewCourses";
//  }

  @GetMapping("/cursussen/toevoegen")
  public String getAddCoursesPage() {
    return "addCourses";
  }

  @PostMapping("/cursussen/toevoegen")
  public String postFileUpload(@RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes) {
    byte[] rawCourses;

    try {
      rawCourses = file.getBytes();
    } catch (IOException e) {
      e.printStackTrace();
      return "error";
    }

    String stringCourses = byteToStringConverter.convertByteArrayToString(rawCourses);
    List<Course> courses = stringToCourseListConverter.convertToCourses(stringCourses);

    PersistedStatistics persistedStatistics = courseService.saveCoursesAndCourseInstances(courses);

    redirectAttributes.addFlashAttribute("persistedStatistics", persistedStatistics);

    return "redirect:/cursusoverzicht";
  }

}
