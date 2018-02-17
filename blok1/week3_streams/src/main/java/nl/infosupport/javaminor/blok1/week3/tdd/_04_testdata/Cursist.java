package nl.infosupport.javaminor.blok1.week3.tdd._04_testdata;

public class Cursist {
  private String gebruikersnaam;
  private String email;
  private boolean active;

  public boolean isActive() {
    return active;
  }

  public void activate() {
    this.active = true;
  }

  public Cursist(String gebruikersnaam, String email) {
    this.gebruikersnaam = gebruikersnaam;
    this.email = email;
  }

  public String getGebruikersnaam() {
    return gebruikersnaam;
  }
  
  public void setGebruikersnaam(String naam) {
    this.gebruikersnaam = naam;
  }
  
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

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

  public int hashCode() {
    return gebruikersnaam.hashCode();
  }
}
