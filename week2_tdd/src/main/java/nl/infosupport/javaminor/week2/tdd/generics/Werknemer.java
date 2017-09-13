package nl.infosupport.javaminor.week2.tdd.generics;

public abstract class Werknemer implements Comparable<Werknemer> {

  protected int id;
  protected String name;

  public Werknemer(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public abstract int getSalaris();

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // return 0   --> gelijk salaris
  // return > 0 --> huidige werknemer salaris is groter
  // return < 0 --> huidige werknemer salaris is kleiner
  @Override
  public int compareTo(Werknemer other) {
    return this.getSalaris() - other.getSalaris();
  }

  @Override
  public String toString() {
    return "Werknemer{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }

}
