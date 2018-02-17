package nl.yoboid.vehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("nl.yoboid.domain")
public class VehicleApplication {

  public static void main(String[] args) {
    SpringApplication.run(VehicleApplication.class, args);
  }

}
