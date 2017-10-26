package week8.jpa_pitfalls.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dept")
public class Department implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "DEPTNO")
  private Long id;

  @Column(name = "DNAME")
  private String name;

  @Column(name = "LOC")
  private String location;

  //bi-directional many-to-one association to Employees
  @OneToMany(mappedBy = "department")
  private List<Employee> employees;

  public Department() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return this.location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public List<Employee> getEmployees() {
    return this.employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  public Employee addEmployee(Employee employee) {
    getEmployees().add(employee);
    employee.setDepartment(this);

    return employee;
  }

  public Employee removeEmployee(Employee employee) {
    getEmployees().remove(employee);
    employee.setDepartment(null);

    return employee;
  }

  @Override
  public String toString() {
    return "Department [id=" + id + ", name=" + name + ", location=" + location + "]";
  }

}