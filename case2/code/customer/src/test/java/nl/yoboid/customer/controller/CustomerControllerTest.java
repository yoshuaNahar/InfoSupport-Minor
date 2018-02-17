package nl.yoboid.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.yoboid.customer.controllers.CustomerController;
import nl.yoboid.customer.repositories.CustomerRepository;
import nl.yoboid.domain.entities.Customer;
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
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

  private static final String VEHICLE_URL = "http://localhost:8080/customers";

  @Autowired
  private MockMvc mvc;

  private ObjectMapper mapper = new ObjectMapper();

  @MockBean
  CustomerRepository repo;

  @Test
  public void getAllCustomersExpectOkAndListOfCustomer() throws Exception {
    Iterable<Customer> testList = Arrays.asList(new Customer(), new Customer());
    when(repo.findAll()).thenReturn(testList);
    mvc.perform(MockMvcRequestBuilders
      .get(VEHICLE_URL))
      .andExpect(status().isOk())
      .andExpect(content().json(mapper.writeValueAsString(testList)));
  }

  @Test
  public void getCustomerByIdExpectOkAndOneCustomer() throws Exception {
    Customer testVehicle = new Customer();
    when(repo.findOne(1L)).thenReturn(testVehicle);
    mvc.perform(MockMvcRequestBuilders
      .get(VEHICLE_URL + "/1"))
      .andExpect(status().isOk())
      .andExpect(content().json(mapper.writeValueAsString(testVehicle)));
  }

  @Test
  public void addNewCustomerExpectOkAndOneVehicle() throws Exception {
    Customer testVehicle = new Customer();
    when(repo.save(any(Customer.class))).thenReturn(testVehicle);
    mvc.perform(MockMvcRequestBuilders
      .post(VEHICLE_URL)
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(mapper.writeValueAsString(testVehicle)))
      .andExpect(status().isOk())
      .andExpect(content().json(mapper.writeValueAsString(testVehicle)));
  }
}
