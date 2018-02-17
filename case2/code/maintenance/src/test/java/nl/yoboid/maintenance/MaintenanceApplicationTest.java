package nl.yoboid.maintenance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MaintenanceApplication.class)
public class MaintenanceApplicationTest {

  @Test
  public void maintenanceServiceMainRunning() {
    MaintenanceApplication.main(new String[]{
      "--spring.main.web-environment=false"
    });
  }

}
