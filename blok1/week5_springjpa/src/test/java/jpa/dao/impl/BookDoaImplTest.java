package jpa.dao.impl;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.context.Config;
import jpa.dao.BookDao;
import jpa.domain.Book;
import jpa.domain.BookCategory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

//@RunWith(SpringJUnit4ClassRunner.class) // zelfde als the extends, maar dan zonder hulp methodes
@ContextConfiguration(classes = Config.class)
public class BookDoaImplTest extends AbstractTransactionalJUnit4SpringContextTests {

  @PersistenceContext
  private EntityManager em; // you can't use a new entityManager with the same transaction

  @Autowired
  private BookDao bookDao;

  // TODO: waarom moeten weer de Transactions hier RequiredNew zijn?
  @Before
  public void setup() {
    addBooks();
  }

  @After
  public void breakDown() {
    removeBooks();
  }

  // TODO: ASk why I'm getting a No EntityManager with actual transaction available for current thread - cannot reliably process 'persist' call
  // Exception here
  @Test
  public void testListBooks() {
    List<Book> books = bookDao.listBooks();

    books.forEach(System.out::println); // testing

    assertThat(books.size(), is(5));
  }

  @Test
  public void testSaveBook() {
    bookDao.saveBook(new Book("title", LocalDate.now(), BookCategory.SPRING, null));

    Book managedBook = bookDao.findBook(6L);

    assertThat(managedBook.getTitle(), is("title"));
    assertThat(countRowsInTable("book"), is(6));
  }

  @Test
  public void testRemoveBookById() {
    bookDao.removeBook(5L);
    em.flush();
    assertThat(countRowsInTable("book"), is(4));
  }

  public void addBooks() {
    em.persist(new Book("Pro JPA 2", LocalDate.of(2013, 5, 25), BookCategory.JPA, null));
    em.persist(
        new Book("Spring in Action 1", LocalDate.of(2005, 8, 19), BookCategory.SPRING, null));
    em.persist(new Book("Spring in Action 2", LocalDate.of(2008, 7, 7), BookCategory.SPRING, null));
    em.persist(new Book("Spring in Action 3", LocalDate.of(2011, 8, 9), BookCategory.SPRING, null));
    em.persist(
        new Book("Spring in Action 4", LocalDate.of(2014, 4, 17), BookCategory.SPRING, null));
    em.flush();
  }

  public void removeBooks() {
    em.createQuery("DELETE FROM Book b").executeUpdate();
    jdbcTemplate.update("ALTER TABLE book AUTO_INCREMENT = 1");
  }

}
