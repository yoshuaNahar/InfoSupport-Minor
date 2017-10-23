package nl.infosupport.javaminor.week7_docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @RequestMapping("/{name}")
  public String home(@PathVariable("name") String name) {
    if (name != null || name.trim().isEmpty()) {
      return "Hello " + name + " from Docker World";
    }
    return "Hello Docker World";
  }

}
