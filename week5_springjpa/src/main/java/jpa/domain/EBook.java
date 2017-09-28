package jpa.domain;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
public class EBook extends Book {

  @ElementCollection
  private Set<EBookFormat> formats;

  public EBook() {
  }

  public EBook(String title, LocalDate releaseDate, BookCategory category, String summary) {
    super(title, releaseDate, category, summary);
  }

  public Set<EBookFormat> getFormats() {
    return formats;
  }

  public void setFormats(Set<EBookFormat> formats) {
    this.formats = formats;
  }

}
