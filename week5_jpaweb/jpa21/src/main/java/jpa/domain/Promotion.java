package jpa.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Promotion {

  @Id
  @GeneratedValue
  private long id;

  private String description;

  private BigDecimal newPrice;

  private LocalDate beginDate;

  private LocalDate endDate;

  @OneToOne(mappedBy = "promotion")
  private Book book;

  public Promotion() {
  }

  public Promotion(String description, BigDecimal newPrice, LocalDate beginDate,
      LocalDate endDate) {
    this.description = description;
    this.newPrice = newPrice;
    this.beginDate = beginDate;
    this.endDate = endDate;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getNewPrice() {
    return newPrice;
  }

  public void setNewPrice(BigDecimal newPrice) {
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
    book.setPromotion(this);
    this.book = book;
  }

}
