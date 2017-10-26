package nl.infosupport.springboot_intro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@Configuration
public class App implements CommandLineRunner {

  // NOTE: with dev-tools you can go to http://localhost:8080/h2-console/
  // and go to jdbc:h2:mem:testdb to go to the database!
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private Logger log;

  public static void main(String[] args) {
    SpringApplication.run(App.class);
  }

  @Bean
  @Scope("prototype")
  public Logger log(InjectionPoint ip) {
    return LoggerFactory.getLogger(ip.getMember().getDeclaringClass().getName());
  }

  @Override
  public void run(String... args) throws Exception {
    jdbcTemplate.execute("DROP TABLE student IF EXISTS");
    jdbcTemplate.execute("CREATE TABLE student(id SERIAL, name VARCHAR(255))");
    jdbcTemplate.update("INSERT INTO student(name) VALUES(?)", "Marie");

    log.info("Finished db stuff");
  }
}
