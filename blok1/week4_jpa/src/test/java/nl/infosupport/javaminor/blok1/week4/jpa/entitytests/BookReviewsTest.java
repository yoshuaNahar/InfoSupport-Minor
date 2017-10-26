package nl.infosupport.javaminor.blok1.week4.jpa.entitytests;

import static nl.infosupport.javaminor.blok1.week4.jpa.daos.EntityManagerFactoryCreator.getEntityManagerFactory;

import javax.persistence.EntityManager;
import nl.infosupport.javaminor.blok1.week4.jpa.entities.Book;
import nl.infosupport.javaminor.blok1.week4.jpa.entities.Review;
import org.junit.Before;
import org.junit.Test;

public class BookReviewsTest {

  private EntityManager em;

  @Before
  public void setup() {
    em = getEntityManagerFactory().createEntityManager();
  }

  @Test
  public void oneToManyReviews() {
    Book book = new Book();
    Review review = new Review();

    em.getTransaction().begin();
//    em.persist();
    em.getTransaction().commit();

    em.clear();

  }

}
