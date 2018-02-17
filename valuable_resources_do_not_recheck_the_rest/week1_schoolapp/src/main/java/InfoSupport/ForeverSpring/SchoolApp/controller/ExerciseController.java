package InfoSupport.ForeverSpring.SchoolApp.controller;

import InfoSupport.ForeverSpring.SchoolApp.domain.CustomUserDetails;
import InfoSupport.ForeverSpring.SchoolApp.domain.Exercise;
import InfoSupport.ForeverSpring.SchoolApp.domain.User;
import InfoSupport.ForeverSpring.SchoolApp.domain.UserExercise;
import InfoSupport.ForeverSpring.SchoolApp.repository.ExerciseRepository;
import InfoSupport.ForeverSpring.SchoolApp.repository.UserExerciseRepository;
import InfoSupport.ForeverSpring.SchoolApp.repository.UserRepository;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exercise")
public class ExerciseController {

  private ExerciseRepository exerciseRepository;
  private UserRepository userRepository;
  private UserExerciseRepository userExerciseRepository;

  @Autowired
  public ExerciseController(ExerciseRepository exerciseRepository, UserRepository userRepository,
      UserExerciseRepository userExerciseRepository) {
    this.exerciseRepository = exerciseRepository;
    this.userRepository = userRepository;
    this.userExerciseRepository = userExerciseRepository;
  }

  @ModelAttribute("page")
  public String module() {
    return "exercises";
  }

  @GetMapping(value = "")
  String index(Model model) {
    model.addAttribute("exercise", exerciseRepository.findAll());

    return "exercise/index";
  }

  @GetMapping(value = "/create")
  String create(Model model) {
    model.addAttribute("exercise", new Exercise());

    return "exercise/create";
  }

  @PostMapping(value = "/create")
  String store(Model model, HttpSession session, @ModelAttribute Exercise exercise) {
    exerciseRepository.save(exercise);

    model.addAttribute("success", "Exercise successfully saved");

    return this.index(model);
  }

  @GetMapping(value = "/contributors/{exerciseId}")
  String contributors(Model model, @PathVariable int exerciseId) {
    Exercise exercise = this.exerciseRepository.findOne(exerciseId);
    model.addAttribute("exercise", exercise);
    model.addAttribute("contributors",
        this.userExerciseRepository.findByExerciseId(exercise.getId()));

    return "exercise/contributors";
  }

  @RequestMapping(value = "/contributors/assign/{exerciseId}")
  String assign(Model model, @PathVariable int exerciseId) {
    Exercise exercise = this.exerciseRepository.findOne(exerciseId);

    CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext()
        .getAuthentication().getPrincipal();
    User user = this.userRepository.findOne(userDetails.getId());

    // make user exercise
    UserExercise userExercise = new UserExercise();
    userExercise.setExercise(exercise);
    userExercise.setUser(user);
    userExercise.setStatus("Started");
    userExercise.setComment("Started");

    exercise.assignUserExercise(userExercise);

    this.userExerciseRepository.save(userExercise);

    model.addAttribute("exercise", exercise);

    return this.contributors(model, exerciseId);
  }

}
