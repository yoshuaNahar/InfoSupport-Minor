package nl.infosupport.javaminor.blok2.week8_springbootdatajpa;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Course {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @OneToMany(fetch = FetchType.EAGER)
  private List<Book> books;

  public Course() {
  }

  public Course(String name, List<Book> books) {
    this.name = name;
    this.books = books;
  }

  @Override
  public String toString() {
    return String.format("Course id=%d name=%s", id, name);
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

}
