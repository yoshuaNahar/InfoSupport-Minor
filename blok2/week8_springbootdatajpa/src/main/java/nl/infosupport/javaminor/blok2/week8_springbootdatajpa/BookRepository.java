package nl.infosupport.javaminor.blok2.week8_springbootdatajpa;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

  List<Book> findByName(String name);

}
