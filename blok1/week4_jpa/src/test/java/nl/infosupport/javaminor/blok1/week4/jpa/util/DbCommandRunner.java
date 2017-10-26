package nl.infosupport.javaminor.blok1.week4.jpa.util;

import java.util.function.Function;
import javax.persistence.EntityManager;

public class DbCommandRunner {

  private DbCommandRunner() {
  }

  public static <T> T run(Function<EntityManager, T> function, EntityManager em) {
    em.getTransaction().begin();

    T t = function.apply(em);

    em.getTransaction().commit();

    return t;
  }

}
