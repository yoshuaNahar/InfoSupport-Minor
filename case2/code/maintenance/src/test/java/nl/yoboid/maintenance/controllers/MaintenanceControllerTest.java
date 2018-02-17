package nl.yoboid.maintenance.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.yoboid.domain.entities.Maintenance;
import nl.yoboid.maintenance.events.senders.ApkCheckFinishedSender;
import nl.yoboid.maintenance.repositories.MaintenanceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MaintenanceController.class)
public class MaintenanceControllerTest {

  private static final String MAINTENANCE_URL = "http://localhost:8080/maintenances";

  @Autowired
  private MockMvc mvc;

  @MockBean
  private ApkCheckFinishedSender sender;

  @MockBean
  private MaintenanceRepository repo;

  @Autowired
  private ObjectMapper mapper;

  @Test
  public void getAllMaintenanceExpectOkAndListOfMaintenance() throws Exception {
    Iterable<Maintenance> testList = Arrays.asList(new Maintenance(), new Maintenance());
    when(repo.findAll()).thenReturn(testList);
    mvc.perform(MockMvcRequestBuilders
      .get(MAINTENANCE_URL))
      .andExpect(status().isOk())
      .andExpect(content().json(mapper.writeValueAsString(testList)));
  }

  @Test
  public void getMaintenanceByIdExpectOkAndOneMaintenance() throws Exception {
    Maintenance testMaintenance = new Maintenance();
    when(repo.findOne(1L)).thenReturn(testMaintenance);
    mvc.perform(MockMvcRequestBuilders
      .get(MAINTENANCE_URL + "/1"))
      .andExpect(status().isOk())
      .andExpect(content().json(mapper.writeValueAsString(testMaintenance)));
  }

  @Test
  public void addNewMaintenanceExpectOkAndOneMaintenance() throws Exception {
    Maintenance testMaintenance = new Maintenance();
    when(repo.save(any(Maintenance.class))).thenReturn(testMaintenance);
    mvc.perform(MockMvcRequestBuilders
      .post(MAINTENANCE_URL)
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(mapper.writeValueAsString(testMaintenance)))
      .andExpect(status().isOk())
      .andExpect(content().json(mapper.writeValueAsString(testMaintenance)));
  }

  @Test
  public void updateStateGivenRequestBodyExpect___() throws Exception {
    Maintenance testMaintenance = new Maintenance();
    when(repo.save(any(Maintenance.class))).thenReturn(testMaintenance);

    mvc.perform(MockMvcRequestBuilders
      .post(MAINTENANCE_URL)
      .content(readResource("maintenanceAndVehicle.json"))
      .contentType(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isOk())
      .andExpect(content().json(mapper.writeValueAsString(testMaintenance)));
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
