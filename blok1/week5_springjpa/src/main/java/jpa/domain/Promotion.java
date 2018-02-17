package jpa.domain;

import java.math.BigDecimal;
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

  @Column(name = "description")
  private String description;

  @Column(name = "new_price")
  private BigDecimal newPrice;

  @Column(name = "begin_date")
  private LocalDate beginDate;

  @Column(name = "end_date")
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
