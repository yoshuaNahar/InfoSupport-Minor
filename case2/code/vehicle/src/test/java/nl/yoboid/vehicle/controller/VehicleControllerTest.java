package nl.yoboid.vehicle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.yoboid.domain.entities.Vehicle;
import nl.yoboid.vehicle.controllers.VehicleController;
import nl.yoboid.vehicle.repositories.VehicleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {
  private static final String VEHICLE_URL = "http://localhost:8080/vehicles";

  @Autowired
  private MockMvc mvc;

  @MockBean
  private VehicleRepository repo;

  private ObjectMapper mapper = new ObjectMapper();

  @Test
  public void getAllVehiclesExpectOkAndListOfVehicles() throws Exception {
    Iterable<Vehicle> testList = Arrays.asList(new Vehicle(), new Vehicle());
    when(repo.findAll()).thenReturn(testList);
    mvc.perform(MockMvcRequestBuilders
      .get(VEHICLE_URL))
      .andExpect(status().isOk())
      .andExpect(content().json(mapper.writeValueAsString(testList)));
  }

  @Test
  public void getVehicleByIdExpectOkAndOneVehicle() throws Exception {
    Vehicle testVehicle = new Vehicle();
    when(repo.findOne(1L)).thenReturn(testVehicle);
    mvc.perform(MockMvcRequestBuilders
      .get(VEHICLE_URL + "/1"))
      .andExpect(status().isOk())
      .andExpect(content().json(mapper.writeValueAsString(testVehicle)));
  }

  @Test
  public void addNewVehicleExpectOkAndOneVehicle() throws Exception {
    Vehicle testVehicle = new Vehicle();
    when(repo.save(any(Vehicle.class))).thenReturn(testVehicle);
    mvc.perform(MockMvcRequestBuilders
      .post(VEHICLE_URL)
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(mapper.writeValueAsString(testVehicle)))
    .andExpect(status().isOk())
    .andExpect(content().json(mapper.writeValueAsString(testVehicle)));
  }
}
