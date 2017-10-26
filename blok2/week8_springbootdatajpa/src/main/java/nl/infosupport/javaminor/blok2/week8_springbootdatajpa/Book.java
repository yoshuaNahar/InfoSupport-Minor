package nl.infosupport.javaminor.blok2.week8_springbootdatajpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  public Book() {
  }

  public Book(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return String.format("Book id=%d name=%s", id, name);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
