package nl.infosupport.javaminor.week2.tdd.generics;

public class TijdelijkeWerknemer extends Werknemer {

  public int aantalUren;
  public int uurLoon;

  public TijdelijkeWerknemer(int id, String name, int aantalUren, int uurLoon) {
    super(id, name);
    this.aantalUren = aantalUren;
    this.uurLoon = uurLoon;
  }

  @Override
  public int getSalaris() {
    return aantalUren * uurLoon;
  }

  public int getAantalUren() {
    return aantalUren;
  }

  public void setAantalUren(int aantalUren) {
    this.aantalUren = aantalUren;
  }

  public int getUurLoon() {
    return uurLoon;
  }

  public void setUurLoon(int uurLoon) {
    this.uurLoon = uurLoon;
  }

  @Override
  public String toString() {
    return "TijdelijkeWerknemer{" +
        "aantalUren=" + aantalUren +
        ", uurLoon=" + uurLoon +
        ", id=" + id +
        ", name='" + name + '\'' +
        '}';
  }

}
