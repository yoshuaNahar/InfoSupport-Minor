package nl.infosupport.javaminor.blok2.week8_springbootrestdemo.controllers;

import java.util.Arrays;
import java.util.List;
import nl.infosupport.javaminor.blok2.week8_springbootrestdemo.entities.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookControllers {

  @GetMapping("/getBooks")
  public List<Book> getBooks() {
    return Arrays.asList(new Book());
  }


}
