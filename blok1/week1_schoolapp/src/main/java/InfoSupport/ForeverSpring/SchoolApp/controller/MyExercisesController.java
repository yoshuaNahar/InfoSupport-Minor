package InfoSupport.ForeverSpring.SchoolApp.controller;

import InfoSupport.ForeverSpring.SchoolApp.domain.Exercise;
import InfoSupport.ForeverSpring.SchoolApp.domain.User;
import InfoSupport.ForeverSpring.SchoolApp.domain.UserExercise;
import InfoSupport.ForeverSpring.SchoolApp.repository.UserExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/myexercises")
public class MyExercisesController {

    private UserExerciseRepository userExerciseRepository;

    @Autowired
    public MyExercisesController(UserExerciseRepository userExerciseRepository) {
        this.userExerciseRepository = userExerciseRepository;
    }

    @GetMapping()
    String index(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<UserExercise> myUserExercises = userExerciseRepository.findByUserId(user.getId());
        List<Exercise> myExercises = new ArrayList<>();
        for (UserExercise userExercise : myUserExercises) {
            myExercises.add(userExercise.getExercise());
        }

        model.addAttribute("exercise", myExercises);

        return "myexercises/index";
    }

    @GetMapping(value = "/edit/{id}")
    String edit(Model model, HttpSession session, @PathVariable int id) {
        UserExercise myUserExercise = userExerciseRepository.findOne(id);

        model.addAttribute("exercise", myUserExercise);

        return "myexercises/edit";
    }

    @PostMapping(value = "/edit")
    String store(Model model, HttpSession session, @ModelAttribute UserExercise userExercise) {
        userExerciseRepository.save(userExercise);

        model.addAttribute("success", "User successfully saved");

        return "myexercises/index";
    }


}
