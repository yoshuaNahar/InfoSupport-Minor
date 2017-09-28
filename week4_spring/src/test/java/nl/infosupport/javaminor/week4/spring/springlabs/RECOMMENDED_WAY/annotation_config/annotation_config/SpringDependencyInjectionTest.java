package nl.infosupport.javaminor.week4.spring.springlabs.RECOMMENDED_WAY.annotation_config.annotation_config;

import nl.infosupport.javaminor.week4.spring.springlabs.RECOMMENDED_WAY.annotation_config.MovieLister;
import nl.infosupport.javaminor.week4.spring.springlabs.RECOMMENDED_WAY.annotation_config.config.AnnotationConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDependencyInjectionTest {

  @Test
  public void initTest() {
    ApplicationContext applicationContext = new
        AnnotationConfigApplicationContext(AnnotationConfig.class);

    MovieLister movieLister = applicationContext.getBean(MovieLister.class);
  }

  @Test
  public void listMovies() {
    ApplicationContext applicationContext = new
        AnnotationConfigApplicationContext(AnnotationConfig.class);

    MovieLister movieLister = applicationContext.getBean(MovieLister.class);
    movieLister.listMovies();
  }

}
