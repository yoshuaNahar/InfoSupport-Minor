package nl.infosupport.javaminor.week3.tdd._01_junit;

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
