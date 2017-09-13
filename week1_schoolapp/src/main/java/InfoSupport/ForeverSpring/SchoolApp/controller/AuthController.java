package InfoSupport.ForeverSpring.SchoolApp.controller;

import InfoSupport.ForeverSpring.SchoolApp.domain.User;
import InfoSupport.ForeverSpring.SchoolApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class AuthController {

    private UserRepository userRepository;

    /**
     * Constructor
     *
     * @param userRepository
     */
    @Autowired
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Map the login request to the login view
     *
     * @param session Required to prevent the session headers to be send prematurely
     * @return auth/login
     */
    @RequestMapping("/login")
    public String login(HttpSession session) {

        return "auth/login";
    }

    /**
     * Mapping of the create page.
     *
     * @param model
     * @return user/edit
     */
    @GetMapping(value = "/register")
    String create(Model model) {
        model.addAttribute("user", new User());

        return "auth/register";
    }

    /**
     * Mapping of the store request.
     * Restore old password when no new password is given.
     *
     * @param model
     * @param session
     * @param user
     * @param password
     * @return user/edit
     */
    @PostMapping(value = "/register")
    String store(Model model, HttpSession session, @ModelAttribute User user, @RequestParam("password") String password) {
        // reset old password when password field was empty and request came from edit page
        if (password.equals("") && session.getAttribute("oldPassword") != null) {
            user.setPasswordWithoutHash(session.getAttribute("oldPassword").toString());
        }

        userRepository.save(user);

        model.addAttribute("success", "User successfully saved");

        return this.login(session);
    }
}
