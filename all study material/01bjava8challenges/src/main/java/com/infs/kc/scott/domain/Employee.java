package com.infs.kc.scott.domain;

import java.util.Date;

public class Employee {

  private Integer id;
  private Double commission;
  private Date hiredate;
  private String name;
  private String job;
  private double salary;
  private Integer mgr_id;
  private Employee manager;
  private int department_id;
  private Department department;

  public Employee(Integer id, Double commission, Date hiredate, String name,
      String job, double salary, Integer mgr_id, int department_id) {
    this.id = id;
    this.commission = commission;
    this.hiredate = hiredate;
    this.name = name;
    this.job = job;
    this.salary = salary;
    this.mgr_id = mgr_id;
    this.department_id = department_id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  Integer getMgr_id() {
    return mgr_id;
  }

  void setMgr_id(Integer mgr_id) {
    this.mgr_id = mgr_id;
  }

  public Double getCommission() {
    return commission;
  }

  public void setCommission(Double commission) {
    this.commission = commission;
  }

  public Date getHiredate() {
    return hiredate;
  }

  public void setHiredate(Date hiredate) {
    this.hiredate = hiredate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  int getDepartment_id() {
    return department_id;
  }

  void setDepartment_id(int department_id) {
    this.department_id = department_id;
  }


  public Employee getManager() {
    return null;
  }

  public void setManager(Employee manager) {
    this.manager = manager;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  @Override
  public String toString() {
    return "Employee [id=" + id + ", commission=" + commission
        + ", hiredate=" + hiredate + ", name=" + name + ", job=" + job
        + ", salary=" + salary + ", mgr_id=" + mgr_id
        + ", department_id=" + department_id + "]";
  }

}
