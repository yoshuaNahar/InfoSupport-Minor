package nl.infosupport.minor.case1_frontend.controller;

import java.util.List;
import nl.infosupport.minor.case1_domain.Cursus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class CursusController {

  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("/")
  public String home() {
    return "index";
  }

  @RequestMapping(value = {"/cursusen"}, method = RequestMethod.GET)
  public String getCursuses(Model model) {
    ResponseEntity<List<Cursus>> cursuses = restTemplate
        .exchange("http://localhost:8081/cursusen", HttpMethod.GET, null,
            new ParameterizedTypeReference<List<Cursus>>() {
            });
    List<Cursus> cursusesBody = cursuses.getBody();

    if (cursusesBody.size() > 0) {
      model.addAttribute("cursuses", cursusesBody);
    }

    return "cursus/index";
  }

}
