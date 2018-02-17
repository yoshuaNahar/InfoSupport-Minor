package nl.yoboid.domain.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Maintenance {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private long vehicleId;

  private LocalDate date;

  private State state;

  public enum State {
    NEW("New"),
    IN_PROGRESS("In Progress"),
    AWAITING_RDW("Awaiting RDW"),
    RDW_CHECK("RDW Check"),
    DONE("Done");

    @Getter
    private String name;

    State(String name) {
      this.name = name;
    }
  }
}
