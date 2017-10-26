package nl.infosupport.javaminor.blok1.week3.tdd._04_testdata;

/**
 * Class die een activiatiecode maakt voor een {@link Cursist}
 * 
 * @author infosupport
 * 
 */
public class ActivatieCodeFactory {

  /**
   * Creert een activatiecode gebaseerd op de gebruikersnaam
   * 
   * @param cursist
   *          cursist waarvoor een activatiecode gegenereerd moet worden
   * @return the activatiecode
   */
  public String createActivatieCode(Cursist cursist) {
    return String.valueOf(Math.abs(cursist.getGebruikersnaam().hashCode()));
  }
}
