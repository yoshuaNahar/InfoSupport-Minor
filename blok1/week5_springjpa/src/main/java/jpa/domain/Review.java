package jpa.domain;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "review")
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "review_id", nullable = false, insertable = false, updatable = false)
  private long id;

  @Column(name = "reviewer_name")
  private String reviewerName;

  @Column(name = "review_date")
  private LocalDate reviewDate;

  @Column(name = "rating")
  private int rating;

  @Column(name = "text")
  private String text;

  @ManyToOne(cascade = {CascadeType.REMOVE})
  private Book book;

  public Review() {
  }

  public Review(String reviewerName, LocalDate reviewDate, int rating, String text, Book book) {
    this.reviewerName = reviewerName;
    this.reviewDate = reviewDate;
    this.rating = rating;
    this.text = text;
    this.book = book;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getReviewerName() {
    return reviewerName;
  }

  public void setReviewerName(String reviewerName) {
    this.reviewerName = reviewerName;
  }

  public LocalDate getReviewDate() {
    return reviewDate;
  }

  public void setReviewDate(LocalDate reviewDate) {
    this.reviewDate = reviewDate;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

}
