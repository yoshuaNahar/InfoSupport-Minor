package nl.infosupport.javaminor.blok1.week3.tdd._02_matchers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CursistRegistratieManager {

  private CursistDao dao = CursistDao.getInstance();

  private ActivatieCodeFactory activatieCodeFactory = new ActivatieCodeFactory();

  private Map<String, Cursist> activaties = new ConcurrentHashMap<String, Cursist>();

  /**
   * Registers a new member (account is left inactive)
   * 
   * @param gebruikersnaam
   *          gebruikersnaam van de nieuwe cursist
   * @param email
   *          emailadres van de cursist
   * @return de activatiecode voor deze nieuwe registratie
   * @throws OngeldigeCursistRegistratie
   *           wanneer de gebruikersnaam al in gebruik is
   */
  public String registreer(String gebruikersnaam, String email)
      throws OngeldigeCursistRegistratie {
    Cursist bestaandeCursist = dao.getCursist(gebruikersnaam);
    if (bestaandeCursist != null) {
      throw new OngeldigeCursistRegistratie();
    }
    Cursist cursist = new Cursist(gebruikersnaam, email);
    String activatieCode = activatieCodeFactory.createActivatieCode(cursist);
    dao.save(cursist);
    activaties.put(activatieCode, cursist);
    return activatieCode;
  }

  /**
   * Activeert een cursist
   * 
   * @param gebruikersnaam
   *          De gebruikersnaam van de cursist
   * @param activatiecode
   *          De bijbehorende activatiecode die verkregen is bij registreren
   * @throws OngeldigeCursistActivatieException
   *           Wanneer de gegeven gebruikersnaam onbekend is of de activatiecode
   *           ongeldig is of wanneer de cursist reeds geactiveerd is.
   */
  public void activeer(String gebruikersnaam, String activatiecode)
      throws OngeldigeCursistActivatieException {
    if (this.activaties.containsKey(activatiecode)) {
      Cursist cursist = activaties.get(activatiecode);
      if (cursist == null
          || !cursist.getGebruikersnaam().equals(gebruikersnaam)
          || cursist.isActive()) {
        throw new OngeldigeCursistActivatieException();
      } else {
        dao.getCursist(gebruikersnaam).activate();
        this.activaties.remove(activatiecode);
      }
    } else {
      throw new OngeldigeCursistActivatieException();
    }
  }

  void setDao(CursistDao dao) {
    this.dao = dao;
  }

}
