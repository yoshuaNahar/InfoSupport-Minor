package nl.infosupport.javaminor.week4.jpa.daos;

import static nl.infosupport.javaminor.week4.jpa.daos.EntityManagerFactoryCreator.*;

import javax.persistence.EntityManager;

public class BookDao {

  private EntityManager em = getEntityManagerFactory().createEntityManager();

}