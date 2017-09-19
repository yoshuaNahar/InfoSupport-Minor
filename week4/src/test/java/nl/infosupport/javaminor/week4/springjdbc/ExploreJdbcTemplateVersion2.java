package nl.infosupport.javaminor.week4.springjdbc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.sql.DataSource;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class ExploreJdbcTemplateVersion2 {

  @Test
  public void createJdbcTemplate() {
    DataSource dataSource = new SimpleDriverDataSource();
    JdbcTemplate template = new JdbcTemplate(dataSource);

    assertThat(template, is(notNullValue()));
  }

  @Test
  public void getAllDepartments() throws Exception {
    SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
    String url = null;
    dataSource.setUrl(url);
    JdbcTemplate template = new JdbcTemplate(dataSource);
  }

}
