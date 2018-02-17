package nl.yoboid.web.viewentity;

import lombok.*;
import nl.yoboid.domain.entities.Maintenance.State;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class MaintenanceViewEntity {

  private long maintenanceId;
  private LocalDate date;
  private String numberPlate;
  private State state;

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    MaintenanceViewEntity comparable = (MaintenanceViewEntity) object;

    return maintenanceId == comparable.maintenanceId &&
      Objects.equals(date, comparable.date) &&
      Objects.equals(numberPlate, comparable.numberPlate) &&
      state == comparable.state;
  }

  @Override
  public int hashCode() {
    return Objects.hash(maintenanceId, date, numberPlate, state);
  }
}
