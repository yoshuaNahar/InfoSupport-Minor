package jpa.domain;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookList {

  private List<Book> books;

  public BookList() {
  }

  public BookList(List<Book> books) {
    this.books = books;
  }

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }

}
