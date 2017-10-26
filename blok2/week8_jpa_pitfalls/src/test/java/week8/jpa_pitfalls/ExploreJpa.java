package week8.jpa_pitfalls;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.junit.Before;
import org.junit.Test;
import week8.jpa_pitfalls.entities.Department;
import week8.jpa_pitfalls.entities.Employee;

public class ExploreJpa {

  private static final String PU = "jpapitfalls";

  private EntityManager em;

  @Before
  public void setup() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
    em = emf.createEntityManager();
  }

  @Test
  public void fetchAllDepartments() {
    String jpqlQueryString = "SELECT d FROM Department d";
    TypedQuery<Department> jpql = em.createQuery(jpqlQueryString, Department.class);
    List<Department> departments = jpql.getResultList();

    departments
        .stream()
        .forEach(System.out::println);
  }

  @Test
  public void fetchAllEmployees() {
    String jpqlQueryString = "SELECT e FROM Employee e";
    TypedQuery<Employee> jpql = em.createQuery(jpqlQueryString, Employee.class);

    List<Employee> employees = jpql.getResultList();
    employees
        .stream()
        .forEach(System.out::println);
  }

}
