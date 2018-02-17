package nl.yoboid.rdw.services;

import nl.yoboid.domain.entities.RdwResponse;
import nl.yoboid.domain.entities.Vehicle;
import nl.yoboid.rdw.configurations.RdwApplicationConfig;
import nl.yoboid.rdw.events.senders.RdwCheckApprovedSender;
import nl.yoboid.rdw.events.senders.RdwCheckDeclinedSender;
import nl.yoboid.rdw.interfaces.RdwCheckEventService;
import nl.yoboid.rdw.interfaces.RdwService;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedInputStream;
import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@Import(RdwApplicationConfig.class)
@RunWith(SpringRunner.class)
public class RdwCheckEventServiceTest {

  @Mock
  private RdwCheckApprovedSender sender;

  @Mock
  private RdwCheckDeclinedSender declinedSender;

  @Mock
  private RdwService service;

  private static final String JSONFILE = "vehicle.json";

  @Test(expected = JSONException.class)
  public void checkResponseAndSendWithNothing() throws IOException {
    RdwCheckEventService rdwCheckEventService = new RdwCheckEventServiceImpl(sender, declinedSender, service);
    rdwCheckEventService.checkResponseAndSend("{}");
  }

  @Test
  public void checkResponseAndSendWithSomething() throws IOException {
    when(service.doRdwRequest(any(Vehicle.class)))
      .thenReturn(new RdwResponse());

    RdwCheckEventService rdwCheckEventService = new RdwCheckEventServiceImpl(sender, declinedSender, service);
    rdwCheckEventService.checkResponseAndSend(readJsonFile(JSONFILE));

    verify(sender, times(1)).send(any());
  }

  @Test
  public void checkResponseAndSendWithSomethingAndRdwResponse_SampleExaminationDateIsNotNull() throws IOException {
    RdwResponse testResponse = new RdwResponse();
    testResponse.setSampleExaminationDate("2017-11-23");
    when(service.doRdwRequest(any(Vehicle.class)))
      .thenReturn(testResponse);

    RdwCheckEventService rdwCheckEventService = new RdwCheckEventServiceImpl(sender, declinedSender, service);
    rdwCheckEventService.checkResponseAndSend(readJsonFile(JSONFILE));

    verify(declinedSender, times(1)).send(any());
  }

  private String readJsonFile(String location){
    Resource resource = new ClassPathResource(location);
    BufferedInputStream stream;
    try {
      stream = new BufferedInputStream(resource.getInputStream());
      return org.apache.commons.io.IOUtils.toString(stream);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
