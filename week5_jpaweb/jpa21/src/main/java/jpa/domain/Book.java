package jpa.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(unique = true)
  @NotBlank
  private String title;

  private LocalDate releaseDate;

  private BookCategory category;

  private String summary;

  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  @XmlTransient
  private Promotion promotion;

  @OneToMany(mappedBy = "book", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @XmlTransient
  private List<Review> reviews;

  @ManyToMany(mappedBy = "books", cascade = CascadeType.PERSIST)
  @XmlTransient
  private List<Author> authors = new ArrayList<>();

  @Embedded
  private BookDetails details = new BookDetails();

  public Book() {
  }

  public Book(String title, LocalDate releaseDate, BookCategory category, String summary) {
    this.title = title;
    this.releaseDate = releaseDate;
    this.category = category;
    this.summary = summary;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  public BookCategory getCategory() {
    return category;
  }

  public void setCategory(BookCategory category) {
    this.category = category;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
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

  public List<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }

  public void addAuthor(Author author) {
    authors.add(author);
    author.getBooks().add(this);
  }

  public BookDetails getDetails() {
    return details;
  }

  public void setDetails(BookDetails details) {
    this.details = details;
  }

}
