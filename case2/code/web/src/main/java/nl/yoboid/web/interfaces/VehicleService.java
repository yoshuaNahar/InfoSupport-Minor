package nl.yoboid.web.interfaces;

import nl.yoboid.domain.entities.Vehicle;

public interface VehicleService {

  Vehicle addVehicle(Vehicle vehicle);

  Vehicle getVehicleById(long vehicleId);
}
