package jpa.dao;

import java.util.List;
import jpa.domain.Book;

public interface BookDao {

  List<Book> findAll();

  void removeBook(long id);

  Book saveBook(Book book);

  Book findBook(long id);

}
