package InfoSupport.ForeverSpring.SchoolApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

  @RequestMapping(value = "/")
  String index(Model model) {
    return "dashboard/index";
  }
}
