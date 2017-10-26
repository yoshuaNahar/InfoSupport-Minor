package jpa.dao;

import java.util.List;
import jpa.domain.Book;

public interface BookDao {

  List<Book> listBooks();

  void removeBook(Long id);

  Book saveBook(Book book);

  Book findBook(Long id);

}
