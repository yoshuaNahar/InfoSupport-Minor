package nl.infosupport.javaminor.week3.tdd._01_junit;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CursistDao {

  private static final CursistDao INSTANCE = new CursistDao();

  private final Map<String, Cursist> cursisten;

  CursistDao(Map<String, Cursist> cursistenMap) {
    this.cursisten = cursistenMap;
  }

  private CursistDao() {
    this(new HashMap<String, Cursist>());
  }

  public static CursistDao getInstance() {
    return INSTANCE;
  }

  public boolean isGeregistreerd(String gebruikersnaam) {
    return cursisten.containsKey(gebruikersnaam);
  }

  public void save(Cursist cursist) {
    Objects.requireNonNull(cursist);
    cursisten.put(cursist.getGebruikersnaam(), cursist);
  }

  public Cursist getCursist(String gebruikersnaam) {
    return cursisten.get(gebruikersnaam);
  }
}
