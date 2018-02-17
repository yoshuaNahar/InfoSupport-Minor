package jpa.dao.impl;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.dao.BookDao;
import jpa.dao.TestDataInserter;
import jpa.domain.Book;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration("classpath:dao-context.xml")
public class BookDaoImplTest extends AbstractTransactionalJUnit4SpringContextTests {

  @Autowired
  private TestDataInserter testDataInserter;

  @Autowired
  private BookDao bookDao;

  @PersistenceContext
  private EntityManager entityManager;

  @Before
  public void setup() {
    testDataInserter.createTestBooks();
  }

  @Test
  public void testListBooks() {
    List<Book> results = bookDao.findAll();

    assertThat(results.size(), is(3));
  }

  @Test
  public void testRemoveBook() {
    bookDao.removeBook(2L);

    entityManager.flush();

    assertThat(countRowsInTable("book"), is(2));
  }

  @Test
  public void testSaveBook() {
    bookDao.saveBook(
        new Book("Java 8 Programmer I Study Guide", LocalDate.of(2015, 11, 25), null, null));

    entityManager.flush();

    assertThat(countRowsInTable("book"), is(4));
  }

  @Test
  public void testFindBook() {
    Book result = bookDao.findBook(1L);

    assertThat(result.getTitle(), is("Pro JPA 2"));
  }

}
