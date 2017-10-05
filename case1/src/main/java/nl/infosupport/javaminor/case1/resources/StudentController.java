package nl.infosupport.javaminor.case1.resources;

import java.util.ArrayList;
import java.util.List;
import nl.infosupport.javaminor.case1.entities.CourseInstance;
import nl.infosupport.javaminor.case1.entities.Student;
import nl.infosupport.javaminor.case1.services.CourseInstanceService;
import nl.infosupport.javaminor.case1.services.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StudentController {

  private static final Logger LOG = LoggerFactory.getLogger(StudentController.class);

  private StudentService studentService;
  private CourseInstanceService courseInstanceService;

  @Autowired
  public StudentController(StudentService studentService,
      CourseInstanceService courseInstanceService) {
    this.studentService = studentService;
    this.courseInstanceService = courseInstanceService;
  }

  @GetMapping("/cursist/toevoegen")
  public String getRegisterStudentPage(Model model) {
    model.addAttribute("student", new Student());

    return "addStudent";
  }

  @PostMapping("/cursist/toevoegen")
  public String registerStudent(@ModelAttribute Student student,
      RedirectAttributes redirectAttributes) {
    studentService.saveStudent(student);

    redirectAttributes.addFlashAttribute("message",
        "Cursist met naam: " + student.getFirstName() + " " + student.getLastName()
            + " is toegevoegd.");

    return "redirect:/cursusoverzicht";
  }

  @GetMapping("/cursist/inschrijven")
  public String getAddStudentToCourseInstancePage(Model model,
      @RequestParam("cursusInstantie") Long courseInstanceId) {
    LOG.info("cursist/inschrijven with courseInstanceId: {}", courseInstanceId);
    CourseInstance currentCourseInstance = courseInstanceService
        .getCourseInstanceById(courseInstanceId);
    if (currentCourseInstance == null) {
      return "redirect:/cursusoverzicht";
    }

    model.addAttribute("courseInstance", currentCourseInstance);

    List<Student> students = studentService.getStudents();
    List<Student> notSignedUpStudents = new ArrayList<>();

    // TODO
    // check for each student if they exist... maybe ask how to do this in db?
    // It is in the lookup table
    for (Student student : students) {
      if (student.getCourseInstances().stream()
          .filter(courseInstance -> courseInstance.getId().equals(courseInstanceId))
          .count() == 0) {
        notSignedUpStudents.add(student);
      }
    }
    model.addAttribute("students", notSignedUpStudents);

    return "addStudentToCourseInstance";
  }

  @PostMapping("/cursist/inschrijven")
  public String addStudentToCourseInstance(@RequestParam("student_id") Long studentId,
      @RequestParam("course_instance_id") Long courseInstanceId) {
    LOG.info("addStudentToCourseInstance with studentId {} and courseInstanceId {}", studentId,
        courseInstanceId);

    CourseInstance courseInstance = courseInstanceService.getCourseInstanceById(courseInstanceId);
    Student student = studentService.getStudentById(studentId);
    student.getCourseInstances().add(courseInstance);

    studentService.addCourseInstance(student);

    return "redirect:/cursusoverzicht";
  }

  // zodat ik de studentDetail pagina kan aanmaken
  @GetMapping("/cursisten")
  public String getStudentsPage(Model model) {
    LOG.info("getStudentsPage");
    List<Student> students = studentService.getStudents();

    model.addAttribute("students", students);

    return "students";
  }

  @GetMapping("/cursist/detail")
  public String getStudentDetailPage(@RequestParam("cursist_id") Long studentId, Model model) {
    LOG.info("getStudentDetailPage");
    Student student = studentService.getStudentById(studentId);

    model.addAttribute("student", student);
    model.addAttribute("courseInstances", student.getCourseInstances());

    return "studentDetail";
  }

}
