package nl.infosupport.javaminor.blok1.week4.jpa.daos;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryCreator {

  private static volatile EntityManagerFactory emf;

  private EntityManagerFactoryCreator() {
  }

  public static EntityManagerFactory getEntityManagerFactory() {
    if (emf == null) {
      synchronized (EntityManagerFactoryCreator.class) {
        if (emf == null) {
          emf = Persistence.createEntityManagerFactory("nl.infosupport.javaminor.week4.jpa.oracle");
        }
      }
    }

    return emf;
  }

}
