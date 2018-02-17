package jpa.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import jpa.dao.BookDao;
import jpa.domain.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BookDaoImpl implements BookDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional(readOnly = true)
  public List<Book> findAll() {
    TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
    return query.getResultList();
  }

  @Override
  @Transactional
  public void removeBook(long id) {
    Query query = entityManager.createQuery("DELETE FROM Book b WHERE b.id = :id");
    query.setParameter("id", id);
    query.executeUpdate();
  }

  @Override
  @Transactional
  public Book saveBook(Book book) {
    return entityManager.merge(book);
  }

  @Override
  @Transactional(readOnly = true)
  public Book findBook(long id) {
    return entityManager.find(Book.class, id);
  }

}
