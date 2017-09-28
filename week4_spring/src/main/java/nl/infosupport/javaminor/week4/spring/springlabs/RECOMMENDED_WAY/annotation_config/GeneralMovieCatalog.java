package nl.infosupport.javaminor.week4.spring.springlabs.RECOMMENDED_WAY.annotation_config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GeneralMovieCatalog implements MovieCatalog {

  @Override
  public List<String> getMovieTitles() {
    List<String> movieTitles = new ArrayList<>();
    movieTitles.add("Titanic");
    movieTitles.add("Avatar");

    return movieTitles;
  }

}