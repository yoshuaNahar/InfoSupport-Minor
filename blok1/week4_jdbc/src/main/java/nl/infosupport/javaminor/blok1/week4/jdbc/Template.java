package nl.infosupport.javaminor.blok1.week4.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nl.infosupport.javaminor.blok1.week4.jdbc.core.Database;

public class Template {

  private final Database db = new Database();

  private Connection conn;
  private PreparedStatement ps;
  private ResultSet rs;

  public <T> List<T> selectAllFrom(String table, Mapper<T> mapper) {
    List<T> entities = new ArrayList<>();
    String sql = "SELECT * FROM " + table;

    try {
      conn = db.getConnection();
      ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();

      while (rs.next()) {
        entities.add(mapper.map(rs));
      }
    } catch (SQLException e) {
      throw new RuntimeException("A problem occurred", e);
    } finally {
      db.releaseResources(conn, ps, rs);
    }

    return entities;
  }

  public <T> T selectById(String sql, int id, Mapper<T> mapper) {
    // String  = "SELECT * FROM " + table + " WHERE " + table + "NO = ?";
    T entity = null;

    try {
      conn = db.getConnection();
      ps = conn.prepareStatement(sql);
      ps.setInt(1, id);
      rs = ps.executeQuery();

      if (rs.next()) {
        entity = mapper.map(rs);
      }
    } catch (SQLException e) {
      throw new RuntimeException("A problem occurred", e);
    } finally {
      db.releaseResources(conn, ps, rs);
    }

    return entity;
  }

}
