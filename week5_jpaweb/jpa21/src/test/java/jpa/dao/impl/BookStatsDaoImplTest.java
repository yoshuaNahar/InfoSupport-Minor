package jpa.dao.impl;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import jpa.dao.BookStatsDao;
import jpa.dao.TestDataInserter;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration("classpath:dao-context.xml")
public class BookStatsDaoImplTest extends AbstractTransactionalJUnit4SpringContextTests {

  @Autowired
  private TestDataInserter testDataInserter;

  @Autowired
  private BookStatsDao bookStatsDao;

  @Before
  public void setup() {
    testDataInserter.createTestBooks();
  }

  @Test
  public void testCountBooks() {
    int result = bookStatsDao.countBooks();

    assertThat(result, is(3));
  }

}
