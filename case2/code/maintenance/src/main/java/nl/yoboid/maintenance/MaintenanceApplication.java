package nl.yoboid.maintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("nl.yoboid.domain")
public class MaintenanceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MaintenanceApplication.class, args);
  }

}
