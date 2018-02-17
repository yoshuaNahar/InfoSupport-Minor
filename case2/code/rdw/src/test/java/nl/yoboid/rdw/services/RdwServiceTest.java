package nl.yoboid.rdw.services;

import nl.yoboid.domain.entities.RdwResponse;
import nl.yoboid.domain.entities.Vehicle;
import nl.yoboid.rdw.interfaces.RdwService;
import nl.yoboid.rdw.repositories.RdwRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.reflect.Field;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.MediaType.APPLICATION_XML;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(RdwService.class)
@AutoConfigureWebClient(registerRestTemplate = true)
public class RdwServiceTest {

  @Value("${rdw.rdw-service-ip}")
  private String url;

  @Autowired
  private RdwService service;

  @MockBean
  private RdwRepository repo;

  @Autowired
  private MockRestServiceServer server;

  @Test
  public void postRdwRequest_GivenVehicle_ExpectRdwResponse() throws Exception {
    Resource resource = new ClassPathResource("rdw_response.xml");
    BufferedInputStream stream = new BufferedInputStream(resource.getInputStream());
    String response = org.apache.commons.io.IOUtils.toString(stream);

    Field f1 = service.getClass().getDeclaredField("fileLocation");
    f1.setAccessible(true);
    f1.set(service, "rdw-request.xml");

    server
      .expect(requestTo(url))
      .andExpect(method(HttpMethod.POST))
      .andExpect(content().contentType(APPLICATION_XML))
      .andRespond(withSuccess(response, APPLICATION_XML));

    Vehicle vehicle = Vehicle.builder()
      .numberPlate("BV-01-EG")
      .mileage(100)
      .customerId(1)
      .build();
    RdwResponse rdwResponse = service.doRdwRequest(vehicle);

    RdwResponse expected = RdwResponse.builder()
      .numberPlate("BV-01-EG")
      .examinationDate("2008-11-19")
      .sampleExaminationDate("2008-11-19")
      .build();

    assertThat(rdwResponse.getNumberPlate(), is(expected.getNumberPlate()));
    assertThat(rdwResponse.getExaminationDate(), is(expected.getExaminationDate()));
    assertThat(rdwResponse.getSampleExaminationDate(), is(expected.getSampleExaminationDate()));

    server.verify();
  }

  @Test(expected = IOException.class)
  public void postRdwRequest_GivenPathToXmlFileDoesntExist_ExpectIOException() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, IOException {

    Field f1 = service.getClass().getDeclaredField("fileLocation");
    f1.setAccessible(true);
    f1.set(service, "../a.xml");

    service.doRdwRequest(new Vehicle());
  }

}
