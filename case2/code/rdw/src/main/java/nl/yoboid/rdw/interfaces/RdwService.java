package nl.yoboid.rdw.interfaces;

import nl.yoboid.domain.entities.RdwResponse;
import nl.yoboid.domain.entities.Vehicle;

import java.io.IOException;

public interface RdwService {

  RdwResponse doRdwRequest(Vehicle vehicle) throws IOException;

}
