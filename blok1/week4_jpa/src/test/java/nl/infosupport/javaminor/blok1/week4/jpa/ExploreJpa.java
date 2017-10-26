//package nl.infosupport.javaminor.week4.jpa;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.notNullValue;
//import static org.junit.Assert.assertThat;
//
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.RollbackException;
//import javax.persistence.TypedQuery;
//import EntityManagerFactoryCreator;
//import Contact;
//import org.junit.Before;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class ExploreJpa {
//
//  private static Logger logger = LoggerFactory.getLogger(ExploreJpa.class);
//  private EntityManager em;
//  private EntityManagerFactory emf;
//
//  @Before
//  public void setUp() {
//    emf = EntityManagerFactoryCreator.getEntityManagerFactory();
//
//    em = emf.createEntityManager();
//  }
//
//  @Test
//  public void createEntityManager() {
//    String persistenceUnitName = "oraclescottpu";
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
//
//    em = emf.createEntityManager();
//
//    assertThat(em, is(notNullValue()));
//  }
//
//  @Test
//  public void createContactEntityEnPersistNaarDatabase() throws Exception {
//    Contact contact = new Contact("Jan", "Dordt");
//
//    em.getTransaction().begin();
//    em.persist(contact);
//    em.getTransaction().commit();
//  }
//
//  @Test
//  public void createContactEntityEnPersistNaarDatabaseScenario2() throws Exception {
//    Contact contact = new Contact("Jan", "Dordt");
//
//    em.persist(contact);
//
//    em.getTransaction().begin();
//    em.getTransaction().commit();
//    //Entity is managed -> Tx slaagt -> wordt in db weggeschreven
//  }
//
//  @Test
//  public void createContactEntityEnPersistNaarDatabaseScenario3() throws Exception {
//    logger.debug("start test {}", "createContactEntityEnPersistNaarDatabaseScenario3");
//    Contact contact = new Contact("Jan", "Dordt");
//
//    em.persist(contact);
//
//    em.getTransaction().begin();
//    em.getTransaction().commit();
//    //In de standalone JPA case zien we dat na een succesvolle commit
//    //blijven de entities managed
//
//    contact.setLocation("Rotterdam");
//    contact = null;
//    em.getTransaction().begin();
//    em.getTransaction().commit();
//  }
//
//
//  @Test
//  public void createContactEntityEnPersistNaarDatabaseScenario4() throws Exception {
//    logger.debug("start test {}", "createContactEntityEnPersistNaarDatabaseScenario3");
//    Contact contact = new Contact("Jan", "Dordt");
//
//    em.persist(contact);
//
//    em.getTransaction().begin();
//    em.getTransaction().rollback();
//    //In de standalone JPA case zien we dat na een niet succesvolle commit
//    //en een rollback die er op volgt -> JPA zal persistenceContext opruimen
//    //alle enities worden unmanaged
//    //blijven de entities managed
//
//    contact.setLocation("Rotterdam");
//
//    em.getTransaction().begin();
//    em.getTransaction().commit();
//  }
//
//  @Test
//  public void createEenDuplicateKeyExceptionScenario() throws Exception {
//    logger.debug("start test {}", "createContactEntityEnPersistNaarDatabaseScenario3");
//
//    initializeerContactTable();
//    //In de standalone JPA case zien we dat na een niet succesvolle commit
//    //en een rollback die er op volgt -> JPA zal persistenceContext opruimen
//    //alle enities worden unmanaged
//    //blijven de entities managed
//
//    Contact contact = new Contact("Piet", "Made");
//
//    em.persist(contact);
//
//    try {
//      em.getTransaction().begin();
//
//      em.getTransaction().commit();
//    } catch (RollbackException e) {
//      logger.error("unexpected exception info {} {}", "info1", "info2");
//      //internal state oppakken
//      em.getTransaction().rollback();
//    }
//  }
//
//  @Test
//  public void createEenMergeScenario() throws Exception {
//    logger.debug("start test {}", "createContactEntityEnPersistNaarDatabaseScenario3");
//
//    initializeerContactTable();
//    //In de standalone JPA case zien we dat na een niet succesvolle commit
//    //en een rollback die er op volgt -> JPA zal persistenceContext opruimen
//    //alle enities worden unmanaged
//    //blijven de entities managed
//
//    Contact detachedContact = new Contact("Jan", "Rotterdam");
//
//    Contact managedContact = em.merge(detachedContact);
//
//    managedContact.setLocation("Made");
//
//    try {
//      em.getTransaction().begin();
//      em.getTransaction().commit();
//    } catch (RollbackException e) {
//      logger.error("unexpected exception info {} {}", "info1", "info2");
//      //internal state oppakken
//      em.getTransaction().rollback();
//    }
//  }
//
//  @Test
//  public void createEenJPQLQueryScenario() throws Exception {
//    logger.debug("start test {}", "createContactEntityEnPersistNaarDatabaseScenario3");
//
//    initializeerContactTable();
//
//    String jpqlString = "SELECT c FROM Contact c";
//    TypedQuery<Contact> jpqlTypedQuery = em.createQuery(jpqlString, Contact.class);
//
//    List<Contact> contacten = jpqlTypedQuery.getResultList();
//
//    contacten.forEach(System.out::println);
//  }
//
//  private void initializeerContactTable() {
//    EntityManager em = emf.createEntityManager();
//    Contact contact = new Contact("Jan", "Dordt");
//
//    em.persist(contact);
//
//    em.getTransaction().begin();
//    em.getTransaction().commit();
//    em.close();
//  }
//
//}
