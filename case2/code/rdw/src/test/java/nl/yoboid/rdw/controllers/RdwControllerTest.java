package nl.yoboid.rdw.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.yoboid.domain.entities.RdwRequestObject;
import nl.yoboid.rdw.configurations.RdwApplicationConfig;
import nl.yoboid.rdw.repositories.RdwRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(RdwApplicationConfig.class)
@RunWith(SpringRunner.class)
@WebMvcTest(RdwController.class)
public class RdwControllerTest {

  private static final String RDW_URL = "http://localhost:8080/rdw";

  @Autowired
  private MockMvc mvc;

  @MockBean
  private RdwRepository repo;

  @Test
  public void getAllExpectListOfRdwRequestObjects() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    Iterable<RdwRequestObject> testList = Arrays.asList(new RdwRequestObject(), new RdwRequestObject());
    when(repo.findAll()).thenReturn(testList);
    mvc.perform(MockMvcRequestBuilders
      .get(RDW_URL))
      .andExpect(status().isOk())
      .andExpect(content().json(mapper.writeValueAsString(testList)));
  }

  @Test
  public void getSingleRdwObjectById() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    RdwRequestObject testObject =new RdwRequestObject();
    when(repo.findOne(1L)).thenReturn(testObject);

    mvc.perform(MockMvcRequestBuilders
      .get(RDW_URL + "/1"))
      .andExpect(status().isOk())
      .andExpect(content().json(mapper.writeValueAsString(testObject)));
  }

  @Test
  public void getSingleRdwObjectByNumberPlate() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    RdwRequestObject testObject =new RdwRequestObject();
    when(repo.findByNumberPlate("ab-cd-ef")).thenReturn(testObject);

    mvc.perform(MockMvcRequestBuilders
      .get(RDW_URL + "/number-plate/ab-cd-ef"))
      .andExpect(status().isOk())
      .andExpect(content().json(mapper.writeValueAsString(testObject)));
  }

  @Test
  public void addNewRdwObject() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    RdwRequestObject testObject =new RdwRequestObject();
    when(repo.save(any(RdwRequestObject.class))).thenReturn(testObject);

    mvc.perform(MockMvcRequestBuilders
      .post(RDW_URL)
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(mapper.writeValueAsBytes(testObject))
    )

      .andExpect(status().isOk())
      .andExpect(content().json(mapper.writeValueAsString(testObject)));
  }
}
