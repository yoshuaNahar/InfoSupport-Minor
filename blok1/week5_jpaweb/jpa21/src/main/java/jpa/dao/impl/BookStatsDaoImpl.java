package jpa.dao.impl;

import javax.sql.DataSource;
import jpa.dao.BookStatsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BookStatsDaoImpl implements BookStatsDao {

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public void setDataSource(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Override
  @Transactional(readOnly = true)
  public int countBooks() {
    return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM book", Integer.class);
  }

}
