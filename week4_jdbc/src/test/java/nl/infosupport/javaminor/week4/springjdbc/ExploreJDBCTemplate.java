package nl.infosupport.javaminor.week4.springjdbc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import oracle.jdbc.OracleDriver;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class ExploreJDBCTemplate {

  @Test
  public void creatingJdbcTemplate() throws SQLException {
    SimpleDriverDataSource dataSource = configureDataSource();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    assertThat(jdbcTemplate, is(notNullValue()));
  }

  @Test
  public void creatingDriverConfigMethode() throws SQLException {
    SimpleDriverDataSource dataSource = configureDataSource();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    assertThat(jdbcTemplate, is(notNullValue()));
  }

  private SimpleDriverDataSource configureDataSource() throws SQLException {
    //create methode init driver
    SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
    dataSource.setDriver(new OracleDriver());
    dataSource.setUrl("jdbc:oracle:thin://localhost/xe");
    dataSource.setUsername("scott");
    dataSource.setPassword("tiger");

    return dataSource;
  }

  @Test
  public void insertRowInContactWithJdbcTemplate() throws Exception {
    //Can you use Reflection here? Let's see
    SimpleDriverDataSource dataSource = configureDataSource();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    String sqlInsert = "INSERT INTO contact (name, email, address, telephone)"
        + " VALUES (?, ?, ?, ?)";
    jdbcTemplate.update(sqlInsert, "Tom", "tomea@mail.com", "USA", "12345");
  }

  @Test
  public void updateRowWithJdbcTemplate() throws Exception {
    SimpleDriverDataSource dataSource = configureDataSource();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    String sqlUpdate = "UPDATE contact set email=? where name=?";
    jdbcTemplate.update(sqlUpdate, "tomee@mail.com", "Tom");
  }

  @Test
  public void selectRowWithJdbcTemplate() throws DataAccessException, SQLException {
    SimpleDriverDataSource dataSource = configureDataSource();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    String sqlSelect = "SELECT * FROM dept";
    List<Contact> listContact = jdbcTemplate.query(sqlSelect, new RowMapper<Contact>() {

      public Contact mapRow(ResultSet result, int rowNum) throws SQLException {
        Contact contact = new Contact();
        contact.setName(result.getString("name"));
        contact.setEmail(result.getString("email"));
        contact.setAddress(result.getString("address"));
        contact.setPhone(result.getString("telephone"));

        return contact;
      }
    });

    listContact.forEach(System.out::println);

    assertThat(listContact.size(), is(1));
  }

  @Test
  public void deleteRowWithJdbcTemplate() throws DataAccessException, SQLException {
    SimpleDriverDataSource dataSource = configureDataSource();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    String sqlDelete = "DELETE FROM contact where name=?";
    jdbcTemplate.update(sqlDelete, "Tom");
  }

  @Test
  public void usingRealDataSource() throws Exception {
    BasicDataSource ds = new BasicDataSource();
    ds.setDriverClassName(OracleDriver.class.getName());
    ds.setUrl("jdbc:oracle:thin:@192.168.1.96:1521:xe");
    ds.setUsername("scott");
    ds.setPassword("tiger");
    int initialSize = 5;
    ds.setInitialSize(initialSize);
    JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
  }

}
