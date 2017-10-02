package nl.infosupport.javaminor.case1.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "student_id", nullable = false, insertable = false, updatable = false)
  private Long id;

  @Column(name = "name", nullable = false)
  // with updatable and insertable, eventhough they have values
  // and you try to merge or persist, they will not!
  // https://stackoverflow.com/questions/3805584/please-explain-about-insertable-false-updatable-false
  private String name;

  @ElementCollection
  @CollectionTable(
      name = "student_books",
      joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "student_id"))
  @Column(name = "book")
  private List<String> books = new ArrayList<>();

  protected Student() {
  }

  public Student(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getBooks() {
    return books;
  }

  public void setBooks(List<String> books) {
    this.books = books;
  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", books=" + books +
        '}';
  }

}
