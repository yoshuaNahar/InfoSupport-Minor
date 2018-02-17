package nl.yoboid.rdw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("nl.yoboid.domain")
public class RdwApplication {

  public static void main(String[] args) {
    SpringApplication.run(RdwApplication.class, args);
  }

}
