package nl.infosupport.javaminor.blok2.week8_springbootdatajpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

public class CriteriaApplication implements CommandLineRunner {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private BookRepository bookRepository;

  public static void main(String[] args) {
    SpringApplication.run(CriteriaApplication.class, args);
  }

  private void showBook(List<Book> bookList) {
    System.out.println("List of books fond with the criteria API");
    for (Book book : bookList) {
      System.out.println(book.getName());
    }
  }

  @Override
  public void run(String... arg0) throws Exception {
    List<Book> springBooks = new ArrayList<>();
    Book book1 = new Book("Spring in Action");
    springBooks.add(book1);
    Book book2 = new Book("Spring Boot in Action");
    springBooks.add(book2);

    List<Book> javaBooks = new ArrayList<>();
    Book book3 = new Book("Core Java");
    javaBooks.add(book3);
    Book book4 = new Book("Head First Java");
    javaBooks.add(book4);

    bookRepository.save(book1);
    bookRepository.save(book2);
    bookRepository.save(book3);
    bookRepository.save(book4);

    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
    Root<Book> fromBookTable = criteriaQuery.from(Book.class);
    criteriaQuery.select(fromBookTable);

    List<Book> bookList = entityManager.createQuery(criteriaQuery).getResultList();

    showBook(bookList);
  }

}
