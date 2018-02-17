package nl.yoboid.rdw.services;

import nl.yoboid.domain.entities.RdwResponse;
import nl.yoboid.domain.entities.Vehicle;
import nl.yoboid.rdw.events.senders.RdwCheckApprovedSender;
import nl.yoboid.rdw.events.senders.RdwCheckDeclinedSender;
import nl.yoboid.rdw.interfaces.RdwCheckEventService;
import nl.yoboid.rdw.interfaces.RdwService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RdwCheckEventServiceImpl implements RdwCheckEventService {

  private final RdwCheckApprovedSender approvedSender;
  private final RdwCheckDeclinedSender declinedSender;
  private final RdwService service;

  @Autowired
  public RdwCheckEventServiceImpl(RdwCheckApprovedSender approvedSender, RdwCheckDeclinedSender declinedSender, RdwService service) {
    this.approvedSender = approvedSender;
    this.declinedSender = declinedSender;
    this.service = service;
  }

  @Override
  public void checkResponseAndSend(String json) throws IOException {
    Vehicle vehicle = convertJsonToVehicle(json);
    RdwResponse response = service.doRdwRequest(vehicle);

    if (response.getSampleExaminationDate() == null) {
      approvedSender.send(Long.toString(vehicle.getId()));
    }
    else {
      declinedSender.send(Long.toString(vehicle.getId()));
    }
  }

  private Vehicle convertJsonToVehicle(String json) {
    JSONObject jsonObject = new JSONObject(json);

    return Vehicle.builder()
      .numberPlate(jsonObject.getString("numberPlate"))
      .mileage(jsonObject.getLong("mileage"))
      .customerId(jsonObject.getLong("customerId")).build();
  }

}
