package nl.infosupport.javaminor.week4.jpa.entitytests;

import static nl.infosupport.javaminor.week4.jpa.daos.EntityManagerFactoryCreator.getEntityManagerFactory;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.persistence.EntityManager;
import nl.infosupport.javaminor.week4.jpa.entities.Book;
import nl.infosupport.javaminor.week4.jpa.entities.Book.Category;
import nl.infosupport.javaminor.week4.jpa.entities.Promotion;
import org.junit.Before;
import org.junit.Test;

public class BookPromotionTest {

  private EntityManager em;

  @Before
  public void setup() {
    em = getEntityManagerFactory().createEntityManager();
  }

  @Test
  public void OneToOnePromotion() {
    em.getTransaction().begin();
    Promotion promotion = new Promotion("description", 1L, LocalDate.now(), LocalDate.now().plus(5,
        ChronoUnit.DAYS));
    Book book = new Book("Title", "Summary", Category.DRAMA, promotion, LocalDate.now());
    em.persist(promotion);
    em.persist(book);
    em.getTransaction().commit();

    em = getEntityManagerFactory().createEntityManager();

    Book managedBook = em.find(Book.class, 1L);
    System.out.println(managedBook);
  }

}
