package nl.infosupport.javaminor.blok1.week3.tdd._03_mocking;

public class Cursist {

  private String gebruikersnaam;
  private String email;
  private boolean active;

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
    // TODO Auto-generated method stub

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

    return gebruikersnaam.equals(cursist.gebruikersnaam);
  }

  public int hashCode() {
    return gebruikersnaam.hashCode();
  }

}
