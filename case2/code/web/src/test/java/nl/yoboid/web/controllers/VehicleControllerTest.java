package nl.yoboid.web.controllers;

import nl.yoboid.domain.entities.Vehicle;
import nl.yoboid.web.configs.WebApplicationConfig;
import nl.yoboid.web.interfaces.MaintenanceService;
import nl.yoboid.web.interfaces.VehicleService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(VehicleController.class)
@Import(WebApplicationConfig.class)
public class VehicleControllerTest {

  private static final String VEHICLE_URL = "http://localhost:8080/voertuig";

  @Autowired
  private MockMvc mvc;

  @MockBean
  private VehicleService vehicleService;

  @MockBean
  private MaintenanceService maintenanceService;

  @Test
  public void addVehicleGivenGetExpectIsOkWithVehicleAttributeAndViewNameIsvehicle_form()
    throws Exception {
    mvc.perform(MockMvcRequestBuilders.get(VEHICLE_URL))
      .andExpect(status().isOk())
      .andExpect(model().attributeExists("vehicle"))
      .andExpect(view().name("vehicle_form"));
  }

  @Test
  public void addVehicleGivenPostAndVehicleObjectExpectIsOkeAndViewNameIsonderhoud() throws Exception {
    when(vehicleService.addVehicle(Matchers.any(Vehicle.class))).thenReturn(Vehicle.builder()
      .numberPlate("AB-12-34")
      .mileage(123)
      .customerId(1)
      .build());

    mvc.perform(MockMvcRequestBuilders.post(VEHICLE_URL)
      .param("numberPlate", "AB-12-34")
      .param("mileage", "123")
      .param("customerId", "1"))
      .andExpect(status().is3xxRedirection())
      .andExpect(view().name("redirect:/onderhoud"))
      .andExpect(flash().attribute("message", is("Voertuig is succesvol opgeslagen.")));
  }
}
