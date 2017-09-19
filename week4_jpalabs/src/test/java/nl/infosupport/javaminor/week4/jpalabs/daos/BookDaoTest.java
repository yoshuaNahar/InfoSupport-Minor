package nl.infosupport.javaminor.week4.jpalabs.daos;

import static nl.infosupport.javaminor.week4.jpalabs.daos.EntityManagerFactoryCreator.getEntityManagerFactory;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;
import nl.infosupport.javaminor.week4.jpalabs.entities.Book;
import org.junit.Before;
import org.junit.Test;

public class BookDaoTest {

  private EntityManager em;

  @Before
  public void setup() {
    em = getEntityManagerFactory().createEntityManager();
  }

  @Test
  public void findBookId1ShouldReturn() {
    createTestBooks();

    Book book = em.find(Book.class, 1L);

    assertThat(book.getTitle(), is("Java2"));
  }

  @Test
  public void updateBookNameShouldReturnNewName() {
//    createTestBooks();


  }

  private void createTestBooks() {
    em.getTransaction().begin();
    em.persist(new Book("Java2"));
    em.persist(new Book("Niet"));
    em.persist(new Book("Kutten"));
    em.persist(new Book("Aan"));
    em.persist(new Book("Mijn"));
    em.persist(new Book("Table"));
    em.getTransaction().commit();
    em.close();

    em = getEntityManagerFactory().createEntityManager();
  }

}
