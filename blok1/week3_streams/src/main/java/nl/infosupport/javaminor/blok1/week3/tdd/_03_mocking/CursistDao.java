package nl.infosupport.javaminor.blok1.week3.tdd._03_mocking;

import java.util.HashMap;
import java.util.Map;

public class CursistDao {

  private Map<String, Cursist> cursisten = new HashMap<>();

  public static CursistDao getInstance() {
    return new CursistDao();
  }

  public boolean isGeregistreerd(String gebruikersnaam) {
    return cursisten.containsKey(gebruikersnaam);
  }

  public void save(Cursist cursist) {
//    cursisten.put(cursist.getGebruikersnaam(), cursist);
  }

  public Cursist getCursist(String gebruikersnaam) {
    return cursisten.get(gebruikersnaam);
  }

}
