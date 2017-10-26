package nl.infosupport.javaminor.blok1.week4.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "address_id", nullable = false, insertable = false, updatable = false)
  private Long id;

  @Column(name = "location", nullable = false)
  private String location;

  @OneToOne(mappedBy = "address")
  private Contact contact;

  public Address() {
  }

  public Address(String location) {
    this.location = location;
  }

  public Address(String location, Contact contact) {
    this.location = location;
    this.contact = contact;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  @Override
  public String toString() {
    return "Address{" +
        "id=" + id +
        ", location='" + location + '\'' +
        '}';
  }

}
