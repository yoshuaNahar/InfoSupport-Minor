package nl.infosupport.javaminor.blok2.week8_springbootdatajpa;

import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private BookRepository bookRepository;

  @Test
  public void testFindByLastName() {
    Book book = new Book("Spring in action");
    entityManager.persist(book);

    List<Book> books = bookRepository.findByName(book.getName());
    assertEquals(1, books.size());
    books = bookRepository.findByName("Book does not exist");
    assertEquals(0, books.size());
  }

}