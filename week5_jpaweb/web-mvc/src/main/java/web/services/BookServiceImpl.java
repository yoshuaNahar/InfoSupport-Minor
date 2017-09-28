package web.services;

import java.util.List;
import jpa.dao.BookDao;
import jpa.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

  private BookDao bookDao;

  @Autowired
  public void setProductRepository(BookDao bookDao) {
    this.bookDao = bookDao;
  }

  @Override
  public List<Book> listAllBooks() {
    return bookDao.findAll();
  }

  @Override
  public Book getBookById(Long id) {
    return bookDao.findBook(id);
  }

  @Override
  public Book saveBook(Book book) {
    return bookDao.saveBook(book);
  }

  @Override
  public void deleteBook(Long id) {
    bookDao.removeBook(id);
  }

}
