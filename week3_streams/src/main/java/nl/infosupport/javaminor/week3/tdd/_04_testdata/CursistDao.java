package nl.infosupport.javaminor.week3.tdd._04_testdata;

import java.util.HashMap;
import java.util.Map;

public class CursistDao {

  private Map<String, Cursist> cursisten = new HashMap<String, Cursist>();

  public static CursistDao getInstance() {
    return new CursistDao();
  }

  public boolean isGeregistreerd(String gebruikersnaam) {
    return cursisten.containsKey(gebruikersnaam);
  }

  public void save(Cursist cursist) {
    // cursisten.put(cursist.getGebruikersnaam(), cursist);
  }

  public Cursist getCursist(String gebruikersnaam) {
    return cursisten.get(gebruikersnaam);
  }
}
