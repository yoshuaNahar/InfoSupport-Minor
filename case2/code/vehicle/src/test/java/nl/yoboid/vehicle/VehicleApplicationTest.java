package nl.yoboid.vehicle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VehicleApplication.class)
public class VehicleApplicationTest {

  @Test
  public void vehicleServiceMainRunning() {
    VehicleApplication.main(new String[] {
      "--spring.main.web-environment=false"
    });
  }

}
