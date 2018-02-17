package nl.yoboid.domain.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RdwRequestObject {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String xmlRequest;
  private String xmlResponse;
  private String numberPlate;

  public RdwRequestObject(String xmlRequest, String xmlResponse, String numberPlate) {
    this.xmlRequest = xmlRequest;
    this.xmlResponse = xmlResponse;
    this.numberPlate = numberPlate;
  }
}
