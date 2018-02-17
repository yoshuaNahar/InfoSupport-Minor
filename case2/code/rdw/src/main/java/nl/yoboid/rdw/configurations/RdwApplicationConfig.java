package nl.yoboid.rdw.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RdwApplicationConfig {

  @Bean
  public RestTemplate template() {
    return new RestTemplate();
  }
}
