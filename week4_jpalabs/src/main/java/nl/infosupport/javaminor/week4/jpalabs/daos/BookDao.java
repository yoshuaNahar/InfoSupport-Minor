package nl.infosupport.javaminor.week4.jpalabs.daos;

import static nl.infosupport.javaminor.week4.jpalabs.daos.EntityManagerFactoryCreator.*;

import javax.persistence.EntityManager;

public class BookDao {

  private EntityManager em = getEntityManagerFactory().createEntityManager();

}
