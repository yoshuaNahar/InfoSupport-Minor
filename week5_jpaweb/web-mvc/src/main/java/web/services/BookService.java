package web.services;

import java.util.List;
import jpa.domain.Book;

public interface BookService {

  List<Book> listAllBooks();

  Book getBookById(Long id);

  Book saveBook(Book product);

  void deleteBook(Long id);

}
