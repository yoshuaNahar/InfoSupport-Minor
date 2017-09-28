package jpa.dao;

import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import jpa.domain.Book;
import jpa.domain.BookCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TestDataInserter {

  private JdbcTemplate jdbcTemplate;

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  public void setDataSource(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void createTestBooks() {
    removeBooks();

    entityManager.persist(new Book("Pro JPA 2", LocalDate.of(2013, 5, 25), BookCategory.JPA, null));
    entityManager.persist(
        new Book("Spring in Action 3", LocalDate.of(2011, 8, 9), BookCategory.SPRING, null));
    entityManager.persist(
        new Book("Spring in Action 4", LocalDate.of(2014, 4, 17), BookCategory.SPRING, null));
  }

  private void removeBooks() {
    Query query = entityManager.createQuery("DELETE FROM Book b");
    query.executeUpdate();

    jdbcTemplate.update("ALTER TABLE book AUTO_INCREMENT = 1");
  }

}
