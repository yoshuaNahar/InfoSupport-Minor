package jpa.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Author {

  @Id
  @GeneratedValue
  private long id;
  private String name;

  @ManyToMany
  private List<Book> books = new ArrayList<>();

  public Author() {
  }

  public Author(String name) {
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }

  public void addBook(Book book) {
    books.add(book);
    book.getAuthors().add(this);
  }

}
