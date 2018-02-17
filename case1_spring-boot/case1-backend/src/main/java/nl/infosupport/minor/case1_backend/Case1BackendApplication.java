package nl.infosupport.minor.case1_backend;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
@EntityScan("nl.infosupport.minor.case1_domain")
public class Case1BackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(Case1BackendApplication.class, args);
  }

  @Bean
  @Scope(SCOPE_PROTOTYPE)
  public Logger logger(InjectionPoint ip) {
    return LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
  }

}
