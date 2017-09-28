package web.controllers;

import java.util.List;
import jpa.dao.BookDao;
import jpa.domain.Book;
import jpa.domain.BookList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookRestController {

  @Autowired
  private BookDao bookDao;

  @RequestMapping(value = "books.xml", method = RequestMethod.GET)
  public BookList listBooks() {
    List<Book> books = bookDao.findAll();
    return new BookList(books);
  }

}
