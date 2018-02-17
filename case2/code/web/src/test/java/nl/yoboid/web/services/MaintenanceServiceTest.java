package nl.yoboid.web.services;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import nl.yoboid.domain.entities.Maintenance;
import nl.yoboid.domain.entities.Maintenance.State;
import nl.yoboid.web.interfaces.MaintenanceService;
import nl.yoboid.web.viewentity.MaintenanceViewEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

@RunWith(SpringRunner.class)
@RestClientTest(MaintenanceService.class)
@AutoConfigureWebClient(registerRestTemplate = true)
public class MaintenanceServiceTest {

  @Value("${service.url.maintenance}")
  private String maintenanceURL;

  @Value("${service.url.vehicle}")
  private String vehicleURL;

  @Autowired
  private MaintenanceService maintenanceService;

  @Autowired
  private MockRestServiceServer server;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void getMaintenancesExpectRequestToServerWithGetAndSingleMaintenance() {
    server
      .expect(requestTo(maintenanceURL))
      .andExpect(method(HttpMethod.GET))
      .andRespond(withSuccess(readResource("maintenance.json"), APPLICATION_JSON_UTF8));

    server
      .expect(requestTo(vehicleURL + "/1"))
      .andExpect(method(HttpMethod.GET))
      .andRespond(withSuccess(readResource("vehicle.json"), APPLICATION_JSON_UTF8));

    List<MaintenanceViewEntity> maintenaces = maintenanceService.getMaintenanceViews();

    MaintenanceViewEntity expectedMaintenanceViewEntity = new MaintenanceViewEntity(0,
      LocalDate.of(2017, 12, 20), "20-AB-12", State.NEW);

    assertThat(maintenaces.size(), is(1));
    assertThat(maintenaces.get(0), is(expectedMaintenanceViewEntity));

    server.verify();
  }

  @Test
  public void updateMaintenanceStateFromNewToInProgressExpectRequestToServerWithPost()
    throws IOException {
    server
      .expect(requestTo(maintenanceURL + "/1"))
      .andExpect(method(HttpMethod.GET))
      .andRespond(withSuccess(readResource("maintenanceInfo.json"), APPLICATION_JSON_UTF8));

    server
      .expect(requestTo(vehicleURL + "/1"))
      .andExpect(method(HttpMethod.GET))
      .andRespond(withSuccess(readResource("vehicle.json"), APPLICATION_JSON_UTF8));

    server.expect(requestTo(maintenanceURL + "/state"))
      .andExpect(method(HttpMethod.POST))
      .andExpect(content().contentType(APPLICATION_JSON_UTF8))
      .andRespond(withSuccess(readResource("maintenanceInfo.json"), APPLICATION_JSON_UTF8));

    maintenanceService.changeStateOfMaintenance(
      objectMapper.readValue(readResource("maintenanceInfo.json"), Maintenance.class));

    server.verify();
  }

  private String readResource(String resourceLocation) {
    Resource resource = new ClassPathResource(resourceLocation);
    BufferedInputStream stream;
    try {
      stream = new BufferedInputStream(resource.getInputStream());
      return org.apache.commons.io.IOUtils.toString(stream);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

}
