package nl.infosupport.javaminor.blok1.week4.jpa.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "promotion")
public class Promotion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "promotion_id", nullable = false, insertable = false, updatable = false)
  private Long id;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "new_price", nullable = false)
  private Long newPrice;

  @Column(name = "begin_date")
  private LocalDate beginDate;

  @Column(name = "end_date")
  private LocalDate endDate;

  @OneToOne(mappedBy = "promotion")
  private Book book;

  public Promotion() {
  }

  public Promotion(String description, Long newPrice) {
    this.description = description;
    this.newPrice = newPrice;
  }

  public Promotion(String description, Long newPrice, LocalDate beginDate,
      LocalDate endDate) {
    this.description = description;
    this.newPrice = newPrice;
    this.beginDate = beginDate;
    this.endDate = endDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getNewPrice() {
    return newPrice;
  }

  public void setNewPrice(Long newPrice) {
    this.newPrice = newPrice;
  }

  public LocalDate getBeginDate() {
    return beginDate;
  }

  public void setBeginDate(LocalDate beginDate) {
    this.beginDate = beginDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  @Override
  public String toString() {
    return "Promotion{" +
        "id=" + id +
        ", description='" + description + '\'' +
        ", newPrice=" + newPrice +
        ", beginDate=" + beginDate +
        ", endDate=" + endDate +
        '}';
  }

}
