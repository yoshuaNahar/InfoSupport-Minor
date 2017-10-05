package nl.infosupport.javaminor.case1.resources;

import java.util.List;
import nl.infosupport.javaminor.case1.entities.CourseInstance;
import nl.infosupport.javaminor.case1.services.CourseInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseInstanceController {

  private static final Logger LOG = LoggerFactory.getLogger(CourseInstanceController.class);

  private CourseInstanceService courseInstanceService;

  @Autowired
  public CourseInstanceController(
      CourseInstanceService courseInstanceService) {
    this.courseInstanceService = courseInstanceService;
  }

  @GetMapping(value = {"/", "/cursusoverzicht"})
  public String getCourseInstancePage(Model model) {
    LOG.info("inside getCourseInstancePage /cursusoverzicht");
    List<CourseInstance> coursesInstances = courseInstanceService.getCoursesInstances();
    model.addAttribute("coursesInstances", coursesInstances);

    LOG.debug("Getting coursesInstances: {}", coursesInstances);

    return "coursesInstances";
  }

  @GetMapping("/cursussen")
  public String getCourseInstanceDetailsPage(@RequestParam("cursusInstantie") Long courseInstanceId,
      Model model) {
    LOG.info("inside getCourseInstanceDetailsPage. courseInstanceId: {}", courseInstanceId);
    CourseInstance courseInstance = courseInstanceService.getCourseInstanceById(courseInstanceId);

    model.addAttribute("courseInstance", courseInstance);

    return "courseInstanceDetail";
  }

}
