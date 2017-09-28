package com.infs.kc.scott.domain;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MemoryScottDB {

  private List<Department> departments;
  private List<Employee> employees;
  private List<SalaryGrades> salaryGrades;

  public List<Department> getDepartments() {
    return departments;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public List<SalaryGrades> getSalaryGrades() {
    return salaryGrades;
  }

  public void initializeDB() {
    initializeDepartemnts();
    initializeEmployees();
    initializeBiDirectinalRelation(employees, departments);
    initializeSalgrades();
  }

  private void initializeDepartemnts() {
    Department[] departments = {
        new Department(10, "ACCOUNTING", "NEW YORK"),
        new Department(20, "RESEARCH", "DALLAS"),
        new Department(30, "SALES", "CHICAGO"),
        new Department(40, "OPERATIONS", "BOSTON")};

    this.departments = Arrays.asList(departments);
  }

  @SuppressWarnings("unused")
  private void initializeEmployees() {
    @SuppressWarnings("deprecation")
    Employee[] employees = {
        new Employee(7369, null, new Date(80, 17, 12), "SMITH",
            "CLERK", 800, 7902, 20),
        new Employee(7499, 300.0, new Date(81, 20, 02), "ALLEN",
            "SALESMAN", 1600, 7698, 30),
        new Employee(7521, 500.0, new Date(81, 22, 02), "WARD",
            "SALESMAN", 1250, 7698, 30),
        new Employee(7566, null, new Date(81, 02, 04), "JONES",
            "MANAGER", 2975, 7839, 20),
        new Employee(7654, 1400.0, new Date(81, 28, 9), "MARTIN",
            "SALESMAN", 1250, 7698, 30),
        new Employee(7698, null, new Date(81, 01, 05), "BLAKE",
            "MANAGER", 2850, 7839, 30),
        new Employee(7782, null, new Date(81, 9, 06), "CLARK",
            "MANAGER", 2450, 7839, 10),
        new Employee(7788, null, new Date(87, 19, 04), "SCOTT",
            "ANALYST", 3000, 7566, 20),
        new Employee(7839, null, new Date(81, 17, 11), "KING",
            "PRESIDENT", 5000, null, 10),
        new Employee(7844, 0.0, new Date(81, 8, 9), "TURNER",
            "SALESMAN", 1500, 7698, 30),
        new Employee(7876, null, new Date(87, 23, 05), "ADAMS",
            "CLERK", 1100, 7788, 20),
        new Employee(7900, null, new Date(81, 03, 12), "JAMES",
            "CLERK", 950, 7698, 30),
        new Employee(7902, null, new Date(81, 03, 12), "FORD",
            "ANALYST", 3000, 7566, 20),
        new Employee(7934, null, new Date(82, 23, 01), "MILLER",
            "CLERK", 1300, 7782, 10)};
    this.employees = Arrays.asList(employees);

    assignManagersToEmployees(employees);
  }

  private void initializeSalgrades() {
    SalaryGrades[] salaryGrades = {new SalaryGrades(1, 1200, 700),
        new SalaryGrades(2, 1400, 1201),
        new SalaryGrades(3, 2000, 1401),
        new SalaryGrades(4, 3000, 2001),
        new SalaryGrades(5, 9999, 3001)};
    this.salaryGrades = Arrays.asList(salaryGrades);
  }

  private void initializeBiDirectinalRelation(List<Employee> employees,
      List<Department> departments) {
    for (Employee employee : employees) {
      for (Department department : departments) {
        if (employee.getDepartment_id() == department.getId()) {
          employee.setDepartment(department);
          department.getEmployees().add(employee);
          break;
        }
      }
    }
  }

  private void assignManagersToEmployees(Employee[] employees) {
    for (Employee employee : employees) {
      if (employee.getManager() == null && employee.getMgr_id() != null) {
        Integer mgr_id = employee.getMgr_id();
        for (Employee manager : employees) {
          if (manager.getId().equals(mgr_id)) {
            employee.setManager(manager);
            break;
          }
        }
      }
    }
  }

}
