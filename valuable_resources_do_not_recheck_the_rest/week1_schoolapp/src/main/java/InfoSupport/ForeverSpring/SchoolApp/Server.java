package InfoSupport.ForeverSpring.SchoolApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * This class is used to run the Spring Boot application. It will start a tomcat server to load all
 * the template files.
 *
 * You can find the server on default: http://localhost:8080.
 */
@EnableSpringDataWebSupport
@SpringBootApplication
public class Server extends WebMvcConfigurerAdapter {

  public static void main(String[] args) {
    SpringApplication.run(Server.class, args);
  }
}
