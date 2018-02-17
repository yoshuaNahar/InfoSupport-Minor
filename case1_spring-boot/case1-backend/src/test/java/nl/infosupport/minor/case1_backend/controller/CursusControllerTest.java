package nl.infosupport.minor.case1_backend.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import nl.infosupport.minor.case1_backend.service.CursusService;
import nl.infosupport.minor.case1_domain.Cursus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(CursusController.class)
public class CursusControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private CursusService cursusService;

  @Test
  public void getCursusen() throws Exception {
    given(cursusService.findCursusen()).willReturn(Collections.checkedList(
        Arrays.asList(
            new Cursus("Cursus 1", "sd", "4 uur", "2017-08-01"),
            new Cursus("Cursus 2", "sd", "4 uur", "2017-08-01")
        ),
        Cursus.class
    ));

    mvc.perform(MockMvcRequestBuilders
        .get("http://localhost:8081/cursusen"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(getResource("cursus.json")));
  }

  private String getResource(String resourceName) throws IOException {
    InputStream is = getClass().getClassLoader().getResourceAsStream(resourceName);
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    return reader
        .lines()
        .collect(Collectors.joining("\n"));
  }

}
