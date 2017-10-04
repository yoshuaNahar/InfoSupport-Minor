package nl.infosupport.javaminor.case1.resources;

import java.util.List;
import nl.infosupport.javaminor.case1.entities.Student;
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

  @Autowired
  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  public String getRegisterStudentPage(Model model) {
    model.addAttribute("student", new Student());

    return "addStudent";
  }

  @PostMapping("/cursist/toevoegen")
  public String registerStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
    studentService.saveStudent(student);

    redirectAttributes.addFlashAttribute("message",
        "Cursist met achternaam " + student.getLastName() + " is toegevoegd.");

    return "redirect:/cursusoverzicht";
  }

  @GetMapping("/cursist/inschrijven")
  public String getAddStudentToCourseInstancePage(Model model, @RequestParam("cursusInstantie") Long courseInstanceId) {
    LOG.debug("cursist/inschrijven with courseInstanceId: {}", courseInstanceId);

    List<Student> students = studentService.getStudents();
    model.addAttribute("students", students);

    return "addStudentToCourseInstance";
  }

  @PostMapping("/cursist/inschrijven")
  public String addStudentToCourseInstance() {

    return "redirect:/cursusoverzicht";
  }

}
