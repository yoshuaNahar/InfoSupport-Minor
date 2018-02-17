package nl.yoboid.web.controllers;

import nl.yoboid.domain.entities.Maintenance;
import nl.yoboid.domain.entities.Vehicle;
import nl.yoboid.web.interfaces.MaintenanceService;
import nl.yoboid.web.interfaces.VehicleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MaintenanceController.class)
public class MaintenanceControllerTest {

  private static final String MAINTENANCE_URL = "http://localhost:8080/onderhoud";

  @Autowired
  private MockMvc mvc;

  @MockBean
  private MaintenanceService maintenanceService;

  @MockBean
  private VehicleService vehicleService;

  @Test
  public void getMaintenanceOverviewGivenGetExpectIsOkWithMaintenancesAttributeAndViewNameIsmaintenance_form()
    throws Exception {
    mvc.perform(MockMvcRequestBuilders.get(MAINTENANCE_URL))
      .andExpect(status().isOk())
      .andExpect(model().attributeExists("maintenanceViewEntities"))
      .andExpect(view().name("maintenance_overview"));
  }

  @Test
  public void getMaintenanceDetailWhenIdGivenExpectIsOkWithMaintenanceAttributeAndViewIsmaintenance_detail()
    throws Exception {
    Maintenance mockMaintenance = new Maintenance();
    mockMaintenance.setVehicleId(1);

    Vehicle mockVehicle = new Vehicle();
    mockVehicle.setNumberPlate("20-AB-12");

    given(maintenanceService.getMaintenanceById(1)).willReturn(mockMaintenance);
    given(vehicleService.getVehicleById(1)).willReturn(mockVehicle);

    mvc.perform(MockMvcRequestBuilders.get(MAINTENANCE_URL + "/1"))
      .andExpect(status().isOk())
      .andExpect(model().attributeExists("vehicle"))
      .andExpect(model().attributeExists("maintenance"))
      .andExpect(view().name("maintenance_detail"));
  }
}
