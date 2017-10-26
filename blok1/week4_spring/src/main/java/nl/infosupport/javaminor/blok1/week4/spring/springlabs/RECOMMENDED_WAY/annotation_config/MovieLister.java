package nl.infosupport.javaminor.blok1.week4.spring.springlabs.RECOMMENDED_WAY.annotation_config;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieLister {

  @Autowired
  @Action
  private MovieCatalog movieCatalog;

  @Autowired
  private Map<String, MovieCatalog> allCatalogs;

  public void listMovies() {
    for(String key : allCatalogs.keySet()) {
      System.out.println(key);
      for(String title : allCatalogs.get(key).getMovieTitles()) {
        System.out.println("-" + title);
      }
    }
  }

  public MovieCatalog getMovieCatalog() {
    return movieCatalog;
  }

}
