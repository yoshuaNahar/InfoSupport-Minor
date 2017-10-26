package nl.infosupport.javaminor.blok1.week4.jdbc;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TemplateTest {

  private Template template;
  private Mapper<Employee> employeeMapper;
  private Mapper<Department> departmentMapper;

  @Before
  public void setup() {
    template = new Template();
    employeeMapper = rs -> new Employee(rs.getInt("empno"), rs.getString("ename"), rs.getString("job"), rs.getInt("mgr"), rs.getDate("hiredate").toLocalDate(), rs.getInt("sal"), rs.getInt("comm"), rs.getInt("deptno"));
    departmentMapper = rs -> new Department(rs.getInt("deptno"), rs.getString("dname"), rs.getString("loc"));

  }

  @Test
  public void selectAllDepartments() {
    List<Department> departments = template.selectAllFrom("DEPT", departmentMapper);

    assertThat(departments.size(), is(3));
  }

  @Test
  public void selectAllEmployees(){
    List<Employee> employees = template.selectAllFrom("EMP", employeeMapper);

    assertThat(employees.size(), is(14));
  }

  @Test
  public void selectEmployeeById() {
    Employee employee = template.selectById("SELECT * FROM EMP WHERE EMPNO = ?", 7369, employeeMapper);

    assertThat(employee.getEmpNo(), is(7369));
    assertThat(employee.geteName(), is("SMITH"));
  }

}
