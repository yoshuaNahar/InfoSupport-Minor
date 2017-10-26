package nl.infosupport.javaminor.blok1.week4.jdbc.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

  public Connection getConnection() {
    try {
      String url = "jdbc:oracle:thin:@192.168.1.96:1521:xe";
      String name = "scott";
      String pass = "tiger";

      return DriverManager.getConnection(url, name, pass);
    } catch (SQLException e) {
      throw new RuntimeException("getting connection failed", e);
    }
  }

  public void releaseResources(Connection conn, PreparedStatement ps, ResultSet rs) {
    try {
      if (rs != null) {
        rs.close();
      }
      if (ps != null) {
        ps.close();
      }
      if (conn != null) {
        conn.close();
      }
    } catch (SQLException e) {
      throw new RuntimeException("Resources could not be closed", e);
    }
  }

}
