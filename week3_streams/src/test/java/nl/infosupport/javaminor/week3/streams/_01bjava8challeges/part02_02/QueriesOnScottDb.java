package nl.infosupport.javaminor.week3.streams._01bjava8challeges.part02_02;

import java.util.Comparator;
import java.util.List;
import nl.infosupport.javaminor.week3.streams._01bjava8challeges.Department;
import nl.infosupport.javaminor.week3.streams._01bjava8challeges.Employee;
import nl.infosupport.javaminor.week3.streams._01bjava8challeges.MemoryScottDB;
import nl.infosupport.javaminor.week3.streams._01bjava8challeges.SalaryGrades;
import org.junit.Before;
import org.junit.Test;

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
  public void showAllEmployees() {
    employees.forEach(System.out::println);
  }

  @Test
  public void showAllDepartments() {
    departments.forEach(System.out::println);
  }

  @Test
  public void showAllSalgrades() {
    salgrades.forEach(System.out::println);
  }

  @Test
  public void printAllEmployeesWithJobIsClerk() {
    employees.stream()
        .filter(employee -> employee.getJob().equals("CLERK"))
        .forEach(System.out::println);
  }

  @Test
  public void printAllEmployeesWithJobIsClerkAndSortOnSalaryAscending() {
    employees.stream()
        .filter(employee -> employee.getJob().equals("CLERK"))
        .sorted((employee1, employee2) -> (int) (employee1.getSalary() - employee2.getSalary()))
        .forEach(System.out::println);
  }

  @Test
  public void printAllEmployeesWithJobIsClerkAndSortOnSalaryDescending() {
    employees.stream()
        .filter(employee -> employee.getJob().equals("CLERK"))
        .sorted(Comparator.comparing(Employee::getSalary).reversed())
        .forEach(System.out::println);
  }

  @Test
  public void printAllUniqueJobsHeldByEmployees() {
    employees.stream()
        .map(Employee::getJob)
        .distinct()
        .forEach(System.out::println);
  }

  @Test
  public void printAllEmployeesWorkingOnDepartment10SortedByName() {
    employees.stream()
        .filter(employee -> employee.getDepartment().getId() == 10)
        .sorted(Comparator.comparing(Employee::getName))
        .forEach(System.out::println);
  }

  @Test
  public void generatedOneStringContainingAllTheSortedNamesOfTheEmployees() {
    employees.stream()
        .map(Employee::getName)
        .reduce((s, s2) -> s + s2)
        .ifPresent(System.out::println);
  }

  @Test
  public void areThereAnyEmployeesBasesInNewYork() {
    employees.stream()
        .filter(employee -> employee.getDepartment().getLocation().equals("NEW YORK"))
        .forEach(System.out::println);
  }

  @Test
  public void printTheHighestSalary() {
    employees.stream()
        .mapToDouble(Employee::getSalary)
        .max()
        .ifPresent(System.out::println);
  }

  @Test
  public void printTheLowestSalary() {
    employees.stream()
        .mapToDouble(Employee::getSalary)
        .min()
        .ifPresent(System.out::println);
  }

  @Test
  public void printTheHighestCommission() {
    employees.stream()
        .mapToDouble(Employee::getCommission)
        .max()
        .ifPresent(System.out::println);
  }

  @Test
  public void printAllEmployeesHavingASalaryInSalaryGrade2() {
    SalaryGrades salGrade2 = salgrades.stream()
        .filter(salaryGrades -> salaryGrades.getGrade() == 2)
        .findFirst()
        .get(); // if null throws an Exception

    employees.stream()
        .filter(employee -> salGrade2.getLowCutoff() < employee.getSalary()
            && salGrade2.getHighCutoff() > employee.getSalary())
        .forEach(System.out::println);
  }

  @Test
  public void printAllEmployeesHavingASalaryInSalaryGrade2SecondAttempt() {
    //TODO implement the code to get the job done
    //This is still a tough one, but should be better to digest when
    //using the special purpose class EmployeeSalaryGrade

    // ???
  }

  @Test
  public void howManyEmployeesAreHavingASalaryInSalaryGrade2Or3() {
    //TODO implement the code to get the job done

  }

  class EmployeeSalaryGrades {

    Employee employee;
    SalaryGrades salaryGrade;

    public EmployeeSalaryGrades(Employee employee, SalaryGrades salaryGrade) {
      this.employee = employee;
      this.salaryGrade = salaryGrade;
    }

    @Override
    public String toString() {
      return "EmployeeSalaryScale [employee=" + employee
          + ", salaryGrade=" + salaryGrade + "]";
    }

  }

}
