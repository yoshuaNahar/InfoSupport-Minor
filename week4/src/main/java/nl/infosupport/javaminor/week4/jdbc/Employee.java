package nl.infosupport.javaminor.week4.jdbc;

import java.time.LocalDate;

public class Employee {

  private int empNo;
  private String eName;
  private String job;
  private int mgr;
  private LocalDate hiredDate;
  private int sal;
  private int comm;
  private int deptNo;

  public Employee(int empNo, String eName, String job, int mgr, LocalDate hiredDate, int sal,
      int comm, int deptNo) {
    this.empNo = empNo;
    this.eName = eName;
    this.job = job;
    this.mgr = mgr;
    this.hiredDate = hiredDate;
    this.sal = sal;
    this.comm = comm;
    this.deptNo = deptNo;
  }

  public int getEmpNo() {
    return empNo;
  }

  public void setEmpNo(int empNo) {
    this.empNo = empNo;
  }

  public String geteName() {
    return eName;
  }

  public void seteName(String eName) {
    this.eName = eName;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public int getMgr() {
    return mgr;
  }

  public void setMgr(int mgr) {
    this.mgr = mgr;
  }

  public LocalDate getHiredDate() {
    return hiredDate;
  }

  public void setHiredDate(LocalDate hiredDate) {
    this.hiredDate = hiredDate;
  }

  public int getSal() {
    return sal;
  }

  public void setSal(int sal) {
    this.sal = sal;
  }

  public int getComm() {
    return comm;
  }

  public void setComm(int comm) {
    this.comm = comm;
  }

  public int getDeptNo() {
    return deptNo;
  }

  public void setDeptNo(int deptNo) {
    this.deptNo = deptNo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Employee employee = (Employee) o;

    return empNo == employee.empNo;
  }

  @Override
  public int hashCode() {
    return empNo;
  }

  @Override
  public String toString() {
    return "Employee{" +
        "empNo=" + empNo +
        ", eName='" + eName + '\'' +
        ", job='" + job + '\'' +
        ", mgr=" + mgr +
        ", hiredDate=" + hiredDate +
        ", sal=" + sal +
        ", comm=" + comm +
        ", deptNo=" + deptNo +
        '}';
  }

}
