package week8.jpa_pitfalls.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "emp")
public class Employee implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "EMPNO")
  private Long id;

  @Column(name = "COMM")
  private BigDecimal commissie;

  @Column(name = "ENAME")
  private String name;

  @Temporal(TemporalType.DATE)
  private Date hiredate;

  private String job;

  @Column(name = "SAL")
  private BigDecimal salary;

  //bi-directional many-to-one association to Department
  @ManyToOne
  @JoinColumn(name = "DEPTNO")
  private Department department;

  //bi-directional many-to-one association to Employees
  @ManyToOne
  @JoinColumn(name = "MGR")
  private Employee manager;

  //bi-directional many-to-one association to Employees
  @OneToMany(mappedBy = "manager")
  private List<Employee> workers;

  public Employee() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getCommissie() {
    return this.commissie;
  }

  public void setCommissie(BigDecimal commissie) {
    this.commissie = commissie;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getHiredate() {
    return this.hiredate;
  }

  public void setHiredate(Date hiredate) {
    this.hiredate = hiredate;
  }

  public String getJob() {
    return this.job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public BigDecimal getSalary() {
    return this.salary;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }

  public Department getDepartment() {
    return this.department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public Employee getManager() {
    return this.manager;
  }

  public void setManager(Employee manager) {
    this.manager = manager;
  }

  public List<Employee> getWorkers() {
    return this.workers;
  }

  public void setWorkers(List<Employee> workers) {
    this.workers = workers;
  }

  public Employee addWorker(Employee worker) {
    getWorkers().add(worker);
    worker.setManager(this);

    return worker;
  }

  public Employee removeWorker(Employee worker) {
    getWorkers().remove(worker);
    worker.setManager(null);

    return worker;
  }

  @Override
  public String toString() {
    return "Employee [id=" + id + ", commissie=" + commissie + ", name=" + name + ", hiredate="
        + hiredate
        + ", job=" + job + ", salary=" + salary + "]";
  }

}