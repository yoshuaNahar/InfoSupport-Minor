package nl.infosupport.javaminor.blok1.week4.jdbc;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class ExploreJdbc {

  private String user = "scott";
  private String password = "tiger";
  private String url = "jdbc:oracle:thin:@192.168.1.96:1521:xe";

  @Test
  public void connectieMakenDeOracleDatabase() throws Exception {
    Connection connection = DriverManager.getConnection(url, user, password);

    assertThat(connection, is(notNullValue()));
  }

  @Test
  public void haalAlleNamenOpVanEmployees() throws Exception {
    Connection connection = DriverManager.getConnection(url, user, password);
    Statement stmt = connection.createStatement();
    String sql = "SELECT * FROM emp";
    ResultSet rs = stmt.executeQuery(sql);

    while (rs.next()) {
      System.out.println(rs.getString("ename"));
    }
  }

  @Test
  public void haalAlleNamenOpVanEmployeesEnSluitConnectionNetjesMaarDoeDatNietDoorExceptionOpTeEten() {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(url, user, password);
      Statement stmt = connection.createStatement();
      String sql = "SELECT * FROM emp";
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        System.out.println(rs.getString("ename"));
      }
    } catch (SQLException ignore) {
      //Doe dit niet
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void haalAlleNamenOpVanEmployeesEnSluitConnectionNetjesMaarPrintException() {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(url, user, password);
      Statement stmt = connection.createStatement();
      String sql = "SELECT * FROM emp";
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        System.out.println(rs.getString("ename"));
      }
    } catch (SQLException sqlexception) {
      //logging
      sqlexception.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void haalAlleNamenOpVanEmployeesEnSluitConnectionNetjesMaarGeefExceptionTerug()
      throws SQLException {
    Connection connection = null;

    try {
      connection = DriverManager.getConnection(url, user, password);
      Statement stmt = connection.createStatement();
      String sql = "SELECT * FROM emp";
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        System.out.println(rs.getString("ename"));
      }
    } catch (SQLException sqlexception) {
      //logging
      //Dingen opruimen
      throw sqlexception;
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void haalAlleNamenOpVanEmployeesEnSluitConnectionNetjesMaarGeefRuntimeExceptionTerug() {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(url, user, password);
      Statement stmt = connection.createStatement();
      String sql = "SELECT * FROM emp";
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        System.out.println(rs.getString("ename"));
      }
    } catch (SQLException sqlexception) {
      //logging
      //Dingen opruimen
      throw new RuntimeException("melding", sqlexception);
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void usingARMAutomaticResourceManagement() {
    String sql = "SELECT * FROM emp";
    //try with resources
    try (Connection connection = DriverManager.getConnection(url, user, password);
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        System.out.println(rs.getString("ename"));
      }
    } catch (SQLException sqlexception) {
      //logging
      //Dingen opruimen
      throw new RuntimeException("melding", sqlexception);
    }
  }

  @Test
  public void lookingBetterToJDBCAbstraction() {
    List<Department> departments = getDepartments();

    for (Department department : departments) {
      System.out.println(department);
    }
  }

  private List<Department> getDepartments() {
    String sql = "SELECT * FROM dept";
    List<Department> departments;
    try (Connection connection = DriverManager.getConnection(url, user, password);
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      departments = new ArrayList<>();
      while (rs.next()) {
        departments
            .add(new Department(rs.getInt("deptno"), rs.getString("dname"), rs.getString("loc")));
      }
    } catch (SQLException sqlexception) {
      //logging
      //Dingen opruimen
      throw new RuntimeException("melding", sqlexception);
    }

    return departments;
  }

  @Test
  public void lookingBetterToJDBCORMAbstraction() {
    Mapper<Department> departmentMapper = rs -> new Department(rs.getInt("deptno"),
        rs.getString("dname"), rs.getString("loc"));

    String sql = "SELECT * FROM dept";
    List<Department> departments = getDepartmentsRevised(sql, departmentMapper);

    for (Department department : departments) {
      System.out.println(department);
    }
  }

  private <T> List<T> getDepartmentsRevised(String sql, Mapper<T> mapper) {
    //String sql = "SELECT * FROM dept";
    List<T> departments;
    try (Connection connection = DriverManager.getConnection(url, user, password);
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      departments = new ArrayList<>();
      while (rs.next()) {
        //departments.add(new Department(rs.getInt("deptno"),rs.getString("dname"),rs.getString("loc")));
        departments.add(mapper.map(rs));
      }
    } catch (SQLException sqlexception) {
      //logging
      //Dingen opruimen
      throw new RuntimeException("melding", sqlexception);
    }

    return departments;
  }

}
