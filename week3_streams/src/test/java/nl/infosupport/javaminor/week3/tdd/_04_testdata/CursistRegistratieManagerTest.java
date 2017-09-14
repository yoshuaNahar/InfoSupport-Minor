//package nl.infosupport.javaminor.week3.tdd._04_testdata;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.notNullValue;
//import static org.junit.Assert.assertThat;
//import static org.mockito.Matchers.eq;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//@RunWith(MockitoJUnitRunner.class)
//public class CursistRegistratieManagerTest {
//
//  private CursistRegistratieManager registratieManager;
//
//  @Mock
//  private CursistDao cursistDao;
//
//  @Before
//  public void before() throws Throwable {
//    registratieManager = new CursistRegistratieManager();
//    registratieManager.setDao(cursistDao);
//  }
//
//  @Test
//  public void cursistOntvangtNaRegistratieEenActivatieCode() throws Throwable {
//    Cursist cursist = null; // Maak hier gebruik van een CursistBuilder om
//                            // de testdata op te bouwen.
//    String registratieCode = registratieManager.registreer(
//        cursist.getGebruikersnaam(), cursist.getEmail());
//
//    assertThat(registratieCode, is(notNullValue()));
//    verify(this.cursistDao).save(eq(cursist));
//  }
//
//  @Test
//  public void cursistKanZichNaRegistrerenLatenActiveren() throws Throwable {
//    Cursist cursist = null; // Maak hier gebruik van een CursistBuilder om
//                            // de testdata op te bouwen.
//    when(cursistDao.getCursist(eq(cursist.getGebruikersnaam()))).thenReturn(
//        null, cursist);
//
//    String activatieCode = registratieManager.registreer(
//        cursist.getGebruikersnaam(), cursist.getEmail());
//    registratieManager.activeer(cursist.getGebruikersnaam(), activatieCode);
//    verify(this.cursistDao).save(eq(cursist));
//  }
//
//  @Test(expected = OngeldigeCursistActivatieException.class)
//  public void managerGeeftFoutBijActiverenVanOnbekendeUser() throws Throwable {
//    String onbekendeUser = "unknownUser";
//    registratieManager.activeer(onbekendeUser, "Activatiecode");
//  }
//
//  @Test(expected = OngeldigeCursistRegistratie.class)
//  public void managerGeeftFoutBijRegistratieVanReedsBestaandeGebruiker()
//      throws Throwable {
//    Cursist cursist = null; // Maak hier gebruik van een CursistBuilder om
//                            // de testdata op te bouwen.
//    when(cursistDao.getCursist(eq(cursist.getGebruikersnaam()))).thenReturn(
//        cursist);
//
//    registratieManager.registreer(cursist.getGebruikersnaam(),
//        cursist.getEmail());
//  }
//
//  @Test(expected = OngeldigeCursistActivatieException.class)
//  public void cursistKanEenmaalGeactiveerdWorden() throws Throwable {
//    Cursist cursist = null; // Maak hier gebruik van een CursistBuilder om
//                            // de testdata op te bouwen.
//    when(cursistDao.getCursist(eq(cursist.getGebruikersnaam()))).thenReturn(
//        null, cursist);
//
//    String activatieCode = registratieManager.registreer(
//        cursist.getGebruikersnaam(), cursist.getEmail());
//    registratieManager.activeer(cursist.getGebruikersnaam(), activatieCode);
//    registratieManager.activeer(cursist.getGebruikersnaam(), activatieCode);
//  }
//
//}
