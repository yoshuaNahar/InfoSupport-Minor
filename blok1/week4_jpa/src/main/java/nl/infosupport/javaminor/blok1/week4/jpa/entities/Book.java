package nl.infosupport.javaminor.blok1.week4.jpa.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "book_id", nullable = false, insertable = false, updatable = false)
  private Long id;

  @Column(name = "title", nullable = false, unique = true)
  private String title;

  @Column(name = "summary")
  private String summary;

  @Column(name = "category")
  @Enumerated(EnumType.STRING)
  private Category category;

  @OneToOne()
  @JoinColumn(name="promotion", referencedColumnName="promotion_id")
  private Promotion promotion;

  @OneToMany(mappedBy = "book", cascade = CascadeType.PERSIST)
  private List<Review> reviews = new ArrayList<>();

  @Column(name = "release_date")
  private LocalDate releaseDate;

  public Book() {
  }

  public Book(String title) {
    this.title = title;
  }

  public Book(Long id, String title) {
    this.id = id;
    this.title = title;
  }

  public Book(String title, String summary,
      Category category, Promotion promotion, LocalDate releaseDate) {
    this.title = title;
    this.summary = summary;
    this.category = category;
    this.promotion = promotion;
    this.releaseDate = releaseDate;
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

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  public Promotion getPromotion() {
    return promotion;
  }

  public void setPromotion(Promotion promotion) {
    this.promotion = promotion;
  }


  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  @Override
  public String toString() {
    return "Book{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", summary='" + summary + '\'' +
        ", category=" + category +
        ", promotion=" + promotion +
        ", releaseDate=" + releaseDate +
        '}';
  }

  public enum Category {

    HORROR, SCIFI, DRAMA

  }

}
