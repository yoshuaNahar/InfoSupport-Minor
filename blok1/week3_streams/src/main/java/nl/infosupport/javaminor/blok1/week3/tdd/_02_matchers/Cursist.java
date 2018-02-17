package nl.infosupport.javaminor.blok1.week3.tdd._02_matchers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Cursist {

  private String gebruikersnaam;
  private String email;
  private boolean active;

  public Cursist() {
  }

  public Cursist(String gebruikersnaam, String email) {
    this.gebruikersnaam = gebruikersnaam;
    this.email = email;
  }

  public boolean isActive() {
    return active;
  }

  public void activate() {
    this.active = true;
  }

  public String getGebruikersnaam() {
    return gebruikersnaam;
  }

  public void setGebruikersnaam(String naam) {
    gebruikersnaam = naam;
  }

  public String getEmail() {
    return email;

  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Cursist{" +
        "gebruikersnaam='" + gebruikersnaam + '\'' +
        ", email='" + email + '\'' +
        ", active=" + active +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Cursist)) {
      return false;
    }

    Cursist cursist = (Cursist) o;
    if (!gebruikersnaam.equals(cursist.gebruikersnaam)) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return gebruikersnaam.hashCode();
  }

}
