package jpa.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BookDetails {

  @Column(name = "pages")
  private int pages;

  @Column(name = "isbn10", unique = true)
  private String isbn10;

  @Column(name = "isbn13", unique = true)
  private String isbn13;

  @Column(name = "language")
  private String language;

  public BookDetails() {
  }

  public BookDetails(int pages, String isbn10, String isbn13, String language) {
    this.pages = pages;
    this.isbn10 = isbn10;
    this.isbn13 = isbn13;
    this.language = language;
  }

  public int getPages() {
    return pages;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public String getIsbn10() {
    return isbn10;
  }

  public void setIsbn10(String isbn10) {
    this.isbn10 = isbn10;
  }

  public String getIsbn13() {
    return isbn13;
  }

  public void setIsbn13(String isbn13) {
    this.isbn13 = isbn13;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

}
