package nl.infosupport.javaminor.blok1.week4.jpa.entitytests;

import static nl.infosupport.javaminor.blok1.week4.jpa.daos.EntityManagerFactoryCreator.getEntityManagerFactory;
import static nl.infosupport.javaminor.blok1.week4.jpa.util.DbCommandRunner.run;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import nl.infosupport.javaminor.blok1.week4.jpa.entities.Book;
import nl.infosupport.javaminor.blok1.week4.jpa.entities.Book.Category;
import org.junit.Before;
import org.junit.Test;

public class BookTest {

  private EntityManager em;

  @Before
  public void setup() {
    em = getEntityManagerFactory().createEntityManager();
    createTestBooks();
  }

  @Test
  public void findBookId1ShouldReturn() {
    Book book = run(em -> em.find(Book.class, 1L), em);

    assertThat(book.getTitle(), is("Java"));
  }

  @Test
  public void updateBookNameShouldReturnNewNameViaFind() {
    Book book = run(em -> em.find(Book.class, 1L), em);

    book.setTitle("Java Other");
    run(em -> null, em);

    Book persistedBook = em.find(Book.class, 1L);

    assertThat(persistedBook.getTitle(), is("Java Other"));
  }

  @Test
  public void updateBookNameShouldReturnNewNameViaMerge() {
    Book book = new Book(1L, "Java Other");

    run(em -> em.merge(book), em);

    Book persistedBook = em.find(Book.class, 1L);

    assertThat(persistedBook.getTitle(), is("Java Other"));
  }

  @Test
  public void getBooksWithJPQL() {
    List<Book> books = em.createQuery("SELECT b FROM Book b", Book.class)
        .getResultList();

    assertThat(books.size(), is(1));
  }

  @Test
  public void createBookWithAllData() {
    Book book1 = new Book(".NET", "Something about .NET"
        , Category.DRAMA, null, LocalDate.now());
    Book book2 = new Book("NEMO", null, Category.HORROR, null, null);

    run(em -> { em.persist(book1); return null; }, em);
    run(em -> { em.persist(book2); return null; }, em);

    List<Book> books = em.createQuery("SELECT b FROM Book b", Book.class)
        .getResultList();

    assertThat(books.size(), is(3));
  }

  @Test
  public void deleteBook() {
    Book book = em.find(Book.class, 1L);

    run(em -> { em.remove(book); return null; }, em);

    em = getEntityManagerFactory().createEntityManager();

    Book nullBook = em.find(Book.class, 1L);

    assertThat(nullBook, is(nullValue()));
  }

  private void createTestBooks() {
    em.getTransaction().begin();
    em.persist(new Book("Java"));
    em.getTransaction().commit();
    em.close();

    em = getEntityManagerFactory().createEntityManager();
  }

}
