package InfoSupport.ForeverSpring.SchoolApp.controller;

import InfoSupport.ForeverSpring.SchoolApp.domain.User;
import InfoSupport.ForeverSpring.SchoolApp.repository.UserRepository;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

  private UserRepository userRepository;

  /**
   * Constructor
   */
  @Autowired
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Defines `module` variable for usage in the sidebar
   *
   * @return String
   */
  @ModelAttribute("page")
  public String module() {
    return "user";
  }

  /**
   * Mapping of the overview page.
   *
   * @return user/index
   */
  @GetMapping(value = "")
  String index(Model model) {
    model.addAttribute("users", userRepository.findAll());

    return "user/index";
  }

  /**
   * Mapping of the create page.
   *
   * @return user/edit
   */
  @GetMapping(value = "/create")
  String create(Model model) {
    model.addAttribute("user", new User());

    return "user/edit";
  }

  /**
   * Mapping of the store request. Restore old password when no new password is given.
   *
   * @return user/edit
   */
  @PostMapping(value = "/create")
  String store(Model model, HttpSession session, @ModelAttribute User user,
      @RequestParam("password") String password) {
    // reset old password when password field was empty and request came from edit page
    if (password.equals("") && session.getAttribute("oldPassword") != null) {
      user.setPasswordWithoutHash(session.getAttribute("oldPassword").toString());
    }

    userRepository.save(user);

    model.addAttribute("success", "User successfully saved");

    return this.index(model);
  }

  /**
   * Mapping of the edit page. Store current password for recovery later.
   *
   * @return user/edit
   */
  @GetMapping(value = "/edit/{id}")
  String edit(Model model, HttpSession session, @PathVariable int id) {
    User user = userRepository.findOne(id);
    model.addAttribute("user", user);
    session.setAttribute("oldPassword", user.getPassword());

    return "user/edit";
  }

  /**
   * Mapping of the delete request.
   *
   * @return user/edit
   */
  @GetMapping(value = "/delete/{id}")
  String delete(Model model, HttpSession session, @PathVariable int id) {
    userRepository.delete(id);

    model.addAttribute("success", "User successfully removed");

    return this.index(model);
  }
}
