package jpa.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import jpa.dao.BookDao;
import jpa.domain.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BookDaoImpl implements BookDao {

  @PersistenceContext
  private EntityManager em;

  @Override
  @Transactional(readOnly = true)
  public List<Book> listBooks() {
    TypedQuery<Book> q = em.createQuery("SELECT b FROM Book b", Book.class);
    return q.getResultList();
  }

  @Override
  public void removeBook(Long id) {
    Book managedBook = findBook(id);
    em.remove(managedBook);
  }

  @Override
  public Book saveBook(Book book) {
    if (em == null) {
      System.out.println("em == null");
    }
    em.persist(book);
    em.flush(); // Why doesn't Spring transaction automatically add the em.flush inside the transaction? Or why isn't Hibernate AUTO flush active?
    return book;
  }

  @Override
  public Book findBook(Long id) {
    return em.find(Book.class, id);
  }

}
