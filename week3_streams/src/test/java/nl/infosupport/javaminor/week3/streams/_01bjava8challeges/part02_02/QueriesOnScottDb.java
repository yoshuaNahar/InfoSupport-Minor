package nl.infosupport.javaminor.week3.streams._01bjava8challeges.part02_02;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import nl.infosupport.javaminor.week3.streams._01bjava8challeges.Department;
import nl.infosupport.javaminor.week3.streams._01bjava8challeges.Employee;
import nl.infosupport.javaminor.week3.streams._01bjava8challeges.MemoryScottDB;
import nl.infosupport.javaminor.week3.streams._01bjava8challeges.SalaryGrades;
import org.junit.Before;
import org.junit.Test;

// TODO: ask why 01bjava8challeges is the same as 02java8collecting
public class QueriesOnScottDb {

  private List<Department> departments;
  private List<Employee> employees;
  private List<SalaryGrades> salgrades;

  @Before
  public void init() {
    MemoryScottDB memoryScottDB = new MemoryScottDB();
    memoryScottDB.initializeDB();

    departments = memoryScottDB.getDepartments();
    employees = memoryScottDB.getEmployees();
    salgrades = memoryScottDB.getSalaryGrades();
  }

  @Test
  public void groupTheEmployeesOnJob() {
    //Read the readme for extra info
  }

  @Test
  public void printJobAndEmployeesWithThisJob() {
    //Read the readme for extra info
  }

  @Test
  public void exploringFromDoubleToInt() throws Exception {
    int result = new Double(115.11).intValue() / 100;

    assertThat(result, is(1));
  }

  @Test
  public void groupTheEmployeesInSalaryCategories() {
    //Read the readme for extra info
    //The above test is a hint on how to define the salary category.
  }

  @Test
  public void groupEmployeeOnSalaryCategoryAndWithinACategoryOnJob() {
    //Read the readme for extra info
  }

  @Test
  public void groupEmployeeOnSalaryCategoryAndWithinACategoryOnJobAndCountTheNumberOfMembersInEachGroup() {
    //Read the readme for extra info
  }

}
