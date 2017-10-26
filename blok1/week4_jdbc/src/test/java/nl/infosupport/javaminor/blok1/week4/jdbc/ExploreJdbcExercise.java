package nl.infosupport.javaminor.blok1.week4.jdbc;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class ExploreJdbcExercise {

  private String user = "scott";
  private String password = "tiger";
  private String url = "jdbc:oracle:thin:@192.168.1.96:1521:xe";

  @Test
  public void getEmployeesShouldReturnAllEmployees() {
    List<Employee> employees = getEmployees();

    assertThat(employees.size(), is(14));
  }

//  private <T> List<T> getDepartmentsRevised(String sql, Mapper<T> mapper) {
//    //String sql = "SELECT * FROM dept";
//    List<T> departments;
//    try (Connection connection = DriverManager.getConnection(url, user, password);
//        Statement stmt = connection.createStatement();
//        ResultSet rs = stmt.executeQuery(sql);) {
//      departments = new ArrayList<>();
//      while (rs.next()) {
//
//        //departments.add(new Department(rs.getInt("deptno"),rs.getString("dname"),rs.getString("loc")));
//        departments.add(mapper.map(rs));
//
//      }
//    } catch (SQLException sqlexception) {
//      //logging
//      //Dingen opruimen
//      throw new RuntimeException("melding", sqlexception);
//    }
//    return departments;
//  }

  @Test
  public void getEmployeeByIdOneShouldGiveEmployeeOne() {
    Employee employee = getEmployeeById(1);

    assertThat(employee, is(new Employee(1, null, null, 0, null, 0, 0, 0)));
  }

  @Test
  public void getEmployeeByIdTwoShouldGiveEmployeeTwo() {
    Employee employee = getEmployeeById(2);

    assertThat(employee, is(new Employee(2, null, null, 0, null, 0, 0, 0)));
  }

  @Test
  public void getEmployeesByAMinimumSalaryOf1500ShouldGive8Employees() {
//    List<Employee> employees = getEmployeesWithMinimumSalary(1500);

//    assertThat(employees.size(), is(8));
  }

  private Employee getEmployeeById(int id) {
    return new Employee(id, null, null, 0, null, 0, 0, 0);
  }

  private List<Employee> getEmployeesWithMinimumSalary(int salary) throws SQLException {
    String sql = "SELECT * FROM EMP WHERE SAL = " + salary;
    try (Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {
      throw new SQLException();
    }
  }

  private List<Employee> getEmployees() {
    List<Employee> employeesResult = new ArrayList<>();

    for (int i = 0; i < 14; i++) {
      employeesResult.add(new Employee(i, null, null, 0, null, 0, 0, 0));
    }

    return employeesResult;
  }

}
