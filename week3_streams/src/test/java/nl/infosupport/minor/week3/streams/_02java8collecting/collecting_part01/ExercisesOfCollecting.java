package nl.infosupport.minor.week3.streams._02java8collecting.collecting_part01;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import nl.infosupport.minor.week3.streams._02java8collecting.Department;
import nl.infosupport.minor.week3.streams._02java8collecting.Employee;
import nl.infosupport.minor.week3.streams._02java8collecting.MemoryScottDB;
import nl.infosupport.minor.week3.streams._02java8collecting.SalaryGrades;
import org.junit.Before;
import org.junit.Test;

public class ExercisesOfCollecting {

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
    Map<String, List<Employee>> employeesByJob = employees.stream()
        .collect(Collectors.groupingBy(Employee::getJob));

    employeesByJob
        .forEach((job, employees) -> {
          System.out.println(job);
          System.out.println();
          System.out.println(employees);
          System.out.println("------------");
        });
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
  public void goupTheEmployeesInSalaryCategories() {
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
