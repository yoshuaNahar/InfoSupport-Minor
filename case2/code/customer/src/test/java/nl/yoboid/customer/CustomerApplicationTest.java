package nl.yoboid.customer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerApplication.class)
public class CustomerApplicationTest {

  @Test
  public void maintenanceServiceMainRunning() {
    CustomerApplication.main(new String[]{
      "--spring.main.web-environment=false"
    });
  }

}
