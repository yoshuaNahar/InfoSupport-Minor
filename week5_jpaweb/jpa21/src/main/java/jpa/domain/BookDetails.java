package jpa.domain;

import javax.persistence.Embeddable;

@Embeddable
public class BookDetails {

  private int pages;
  private int isbn10;
  private int isbn13;
  private String language;

  public BookDetails() {
  }

  public BookDetails(int pages, int isbn10, int isbn13, String language) {
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

  public int getIsbn10() {
    return isbn10;
  }

  public void setIsbn10(int isbn10) {
    this.isbn10 = isbn10;
  }

  public int getIsbn13() {
    return isbn13;
  }

  public void setIsbn13(int isbn13) {
    this.isbn13 = isbn13;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

}
