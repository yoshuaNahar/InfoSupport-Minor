package nl.infosupport.javaminor.blok1.week3.tdd._01_junit;

public class CursistRegistratieManager {

  private CursistDao dao = CursistDao.getInstance();

  private ActivatieCodeFactory activatieCodeFactory = new ActivatieCodeFactory();

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
    return null;
  }

  /**
   * Activeert een cursist
   * 
   * @param gebruikersnaam
   *          De gebruikersnaam van de cursist
   * @param activationcode
   *          De bijbehorende activatiecode die verkregen is bij registreren
   * @throws OngeldigeCursistActivatieException
   *           Wanneer de gegeven gebruikersnaam onbekend is of de activatiecode
   *           ongeldig is of wanneer de cursist reeds geactiveerd is.
   */
  public void activeer(String gebruikersnaam, String activationcode)
      throws OngeldigeCursistActivatieException {
  }

}
