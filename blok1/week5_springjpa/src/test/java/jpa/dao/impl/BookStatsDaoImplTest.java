package jpa.dao.impl;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.context.Config;
import jpa.dao.BookStatsDao;
import jpa.domain.Book;
import jpa.domain.BookCategory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class BookStatsDaoImplTest {

  @Autowired
  public BookStatsDao bookStatsDao;
  @PersistenceContext
  private EntityManager em;

  @Before
  public void setup() {
    addBooks();
  }

  @Test
  public void testCountBooksJdbcTemplate() {
    assertThat(bookStatsDao.countBooks(), is(5));
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

}
