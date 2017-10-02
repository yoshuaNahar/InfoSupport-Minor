package nl.infosupport.javaminor.case1.daos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nl.infosupport.javaminor.case1.config.AppConfig;
import nl.infosupport.javaminor.case1.entities.Student;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(classes = AppConfig.class)
public class ClassDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

  @Autowired
  private StudentDao studentDao;

  @PersistenceContext
  private EntityManager em;

  @Test
  public void addStudentsInAClass() {
    Student student1 = new Student("ASD");
    List<String> books = new ArrayList<>();
    books.add("Java Book");
    books.add("C# Book");
    student1.setBooks(books);

    Student student2 = new Student("ASD2");

    List<Student> students = new ArrayList<>();
    students.add(student1);
    students.add(student2);

    studentDao.save(student1);
    studentDao.save(student2);
  }

  @Test
  public void addStudentAndChangeStudentNameShouldThrowNotUpdatableException() {
    Student student = new Student("Student");
    studentDao.save(student);
    em.flush();
    em.clear();

    Student persistedStudent = studentDao.getById(6L);
    System.out.println(persistedStudent);
    persistedStudent.setName("Other Name");
    studentDao.merge(persistedStudent);
    em.flush();
  }

  @Test
  public void selectStudentAndSetBooksCollectionToNull() {
    Student student = studentDao.getById(3L);
    List<String> books = student.getBooks();

    books.forEach(System.out::println);

    student.setBooks(null);

    em.flush();
  }

  @Test
  public void selectStudentAndAddBookInCollection() {
    Student student = studentDao.getById(3L);
    List<String> books = student.getBooks();

    books.add("PHP Book");

    books.forEach(System.out::println);

    em.flush();
  }

  @Test
  public void selectStudentAndChangeCollection() {
    Student student = studentDao.getById(3L);
    List<String> newBooks = new ArrayList<>();

    newBooks.add("Rocket Engineering");

    newBooks.forEach(System.out::println);

    student.setBooks(newBooks);

    em.flush();
  }

}
