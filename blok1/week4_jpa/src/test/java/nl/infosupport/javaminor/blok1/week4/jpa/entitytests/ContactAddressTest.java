package nl.infosupport.javaminor.blok1.week4.jpa.entitytests;

import javax.persistence.EntityManager;
import nl.infosupport.javaminor.blok1.week4.jpa.daos.EntityManagerFactoryCreator;
import nl.infosupport.javaminor.blok1.week4.jpa.entities.Address;
import nl.infosupport.javaminor.blok1.week4.jpa.entities.Contact;
import org.junit.Before;
import org.junit.Test;

public class ContactAddressTest {

  private EntityManager em;

  @Before
  public void setup() {
    em = EntityManagerFactoryCreator.getEntityManagerFactory().createEntityManager();
  }

  @Test
  public void createContactWithAddress() {
    Address address = new Address("Made");
    Contact contact = new Contact("Jan", address);

    em.getTransaction().begin();
    em.persist(address);
    em.persist(contact);
    em.getTransaction().commit();

    em.close();
    em = EntityManagerFactoryCreator.getEntityManagerFactory().createEntityManager();

    Contact managedContact = em.find(Contact.class, 1L);
    System.out.println(managedContact);
//    Address managedAddress = em.find(Address.class, 1L);

//    System.out.println(managedAddress);
//    System.out.println(managedAddress.getContact());

//    assertThat(managedContact.getId(), is(1L));
//    assertThat(managedContact.getAddress().getId(), is(1L));

    // TODO: Check why 2 select queries!!
  }

  // TODO: set persistence.xml back to Oracle
}
