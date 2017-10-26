package nl.infosupport.javaminor.blok2.week8_springbootdatajpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JPAApplication {

  @PersistenceContext
  private EntityManager entityManager;

  public static void main(String[] args) {
    SpringApplication.run(JPAApplication.class, args);
  }

  // Uitbreiden met courses die geen boek hebben en boeken die geen course hebben
  @SuppressWarnings("unchecked")
  @Bean
  public CommandLineRunner demo(CourseRepository courseRepository, BookRepository bookRepository) {
    return (args) -> {
      List<Book> springBooks = new ArrayList<Book>();
      Book book1 = new Book("Spring in Action");
      springBooks.add(book1);
      Book book2 = new Book("Spring Boot in Action");
      springBooks.add(book2);

      List<Book> javaBooks = new ArrayList<Book>();
      Book book3 = new Book("Core Java");
      javaBooks.add(book3);
      Book book4 = new Book("Head First Java");
      javaBooks.add(book4);

      bookRepository.save(book1);
      bookRepository.save(book2);
      bookRepository.save(book3);
      bookRepository.save(book4);

      courseRepository.save(new Course("Spring", springBooks));
      courseRepository.save(new Course("Java", javaBooks));

      System.out.println("\n Using the repository to find all courses");
      Iterable<Course> courseIterable = courseRepository.findAll();
      System.out
          .println("Number of courses: " + courseIterable.spliterator().getExactSizeIfKnown());

      System.out.println("\n Using JPQL to find all courses");
      List<Course> entityManagerCourses = (List<Course>) entityManager
          .createQuery("SELECT c FROM Course c")
          .getResultList();
      System.out.println("Number of courses: " + entityManagerCourses.size());
      showCourses(entityManagerCourses);

      System.out.println("\n Using JPQL join fetch to find all courses");
      List<Course> entityManagerCoursesJoinFetch = (List<Course>) entityManager
          .createQuery("SELECT c FROM Course c join fetch c.books")
          .getResultList();
      System.out.println("Number of courses: " + entityManagerCoursesJoinFetch.size());
      showCourses(entityManagerCoursesJoinFetch);

//			System.out.println("\n Using JPQL distinct join fetch to find all courses");
//			List<Course> entityManagerCoursesJoinFetchDistinct = (List<Course>)entityManager.createQuery("SELECT DISTINCT c FROM Course c join fetch c.books").getResultList();
//			System.out.println("Number of courses: " + entityManagerCoursesJoinFetchDistinct.size());
//			showCourses(entityManagerCoursesJoinFetchDistinct);
//			
//			
//			System.out.println("\n Using JPQL left join to find all courses");
//			List<Course> entityManagerCoursesLeftJoin = (List<Course>)entityManager.createQuery("SELECT c FROM Course c left join c.books").getResultList();
//			System.out.println("Number of courses: " + entityManagerCoursesLeftJoin.size());	
//			showCourses(entityManagerCoursesLeftJoin);
//			
//			System.out.println("\n Using JPQL right join to find all courses");
//			List<Course> entityManagerCoursesRightJoin = (List<Course>)entityManager.createQuery("SELECT c FROM Course c right join c.books").getResultList();
//			System.out.println("Number of courses: " + entityManagerCoursesRightJoin.size());	
//			showCourses(entityManagerCoursesRightJoin);
//			
//			System.out.println("\n Using JPQL inner join to find all courses");
//			List<Course> entityManagerCoursesInnerJoin = (List<Course>)entityManager.createQuery("SELECT c FROM Course c inner join c.books").getResultList();
//			System.out.println("Number of courses: " + entityManagerCoursesInnerJoin.size());	
//			showCourses(entityManagerCoursesInnerJoin);
//
//			
//			for (int i = 0; i< 100; i++) {
//				Course course = new Course("Testcourse " + i, null);
//				courseRepository.save(course);
//			}
//			
//			System.out.println("\n Using JPQL to find a large amount of courses");			
//			List<Course> largeAmountOfCourses = (List<Course>)entityManager.createQuery("SELECT c FROM Course c").getResultList();
//			System.out.println("Number of courses: " + largeAmountOfCourses.size());
//			
//			System.out.println("\n Using JPQL distinct fetch join to find a large amount of courses");			
//			List<Course> largeAmountOfCoursesJoinFetch = (List<Course>)entityManager.createQuery("SELECT distinct c FROM Course c join fetch c.books").getResultList();
//			System.out.println("Number of courses: " + largeAmountOfCoursesJoinFetch.size());
//			
//			System.exit(0);
    };
  }

  private void showCourses(List<Course> courses) {
    for (Course course : courses) {
      System.out.println(
          course.getName() + " : " + course.getBooks().get(0).getName() + " | " + course.getBooks()
              .get(1).getName());
    }
  }

}
