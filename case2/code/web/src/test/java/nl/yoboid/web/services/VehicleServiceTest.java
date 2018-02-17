package nl.yoboid.web.services;

import nl.yoboid.domain.entities.Vehicle;
import nl.yoboid.web.interfaces.VehicleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(VehicleService.class)
@AutoConfigureWebClient(registerRestTemplate = true)
public class VehicleServiceTest {

  @Autowired
  private VehicleService vehicleService;

  @Autowired
  private MockRestServiceServer server;

  @Value("${service.url.vehicle}")
  private String vehicleServiceUrl;

  @Test
  public void addVehicleGivenVehicleExpectRequestToServerWithPostAndSuccessResponseTrue() {
    server
      .expect(requestTo(vehicleServiceUrl))
      .andExpect(method(HttpMethod.POST))
      .andRespond(withSuccess("{ \"numberPlate\":\"12-AB-34\",\"customerId\":1,\"mileage\":100 }", APPLICATION_JSON));

    Vehicle vehicle = new Vehicle(1, "12-AB-34", 1, 100);

    Vehicle newVehicle = vehicleService.addVehicle(vehicle);

    assertThat(newVehicle.getNumberPlate(), is(vehicle.getNumberPlate()));
    assertThat(newVehicle.getCustomerId(), is(vehicle.getCustomerId()));
    assertThat(newVehicle.getMileage(), is(vehicle.getMileage()));

    server.verify();
  }

}
