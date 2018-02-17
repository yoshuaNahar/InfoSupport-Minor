package nl.yoboid.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class WebApplicationTest {

  @Test
  public void webApplicationMainRunning() {
    WebApplication.main(new String[] {
      "--spring.main.web-environment=false",
    });
  }
}
