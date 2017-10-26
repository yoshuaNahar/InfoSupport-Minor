package jpa.dao.impl;

import jpa.dao.BookStatsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BookStatsDaoImpl implements BookStatsDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public int countBooks() {
    return jdbcTemplate.update("SELECT COUNT(book_id) FROM book");
  }

}
