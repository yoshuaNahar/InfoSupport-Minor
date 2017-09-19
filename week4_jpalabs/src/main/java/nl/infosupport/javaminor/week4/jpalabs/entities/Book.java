package nl.infosupport.javaminor.week4.jpalabs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "book_id", nullable = false, insertable = false, updatable = false)
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

//  private Rating rating;

  public Book() {
  }

  public Book(String title) {
    this.title = title;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "Book{" +
        "id=" + id +
        ", title='" + title + '\'' +
        '}';
  }

}
