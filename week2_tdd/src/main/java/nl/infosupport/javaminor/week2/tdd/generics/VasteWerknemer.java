package nl.infosupport.javaminor.week2.tdd.generics;

public class VasteWerknemer extends Werknemer {

  public int salarisSchaal;

  public VasteWerknemer(int id, String name, int salarisSchaal) {
    super(id, name);
    this.salarisSchaal = salarisSchaal;
  }

  @Override
  public int getSalaris() {
    return salarisSchaal * 1000;
  }

  public int getSalarisSchaal() {
    return salarisSchaal;
  }

  public void setSalarisSchaal(int salarisSchaal) {
    this.salarisSchaal = salarisSchaal;
  }

  @Override
  public String toString() {
    return "VasteWerknemer{" +
        "salarisSchaal=" + salarisSchaal +
        ", id=" + id +
        ", name='" + name + '\'' +
        '}';
  }

}
