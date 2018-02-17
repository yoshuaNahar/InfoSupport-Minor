package nl.yoboid.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VehicleMaintenanceWrapper {

    private Vehicle vehicle;
    private Maintenance maintenance;

}
