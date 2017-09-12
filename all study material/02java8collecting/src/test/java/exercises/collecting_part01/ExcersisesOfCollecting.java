package exercises.collecting_part01;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.infs.kc.scott.domain.Department;
import com.infs.kc.scott.domain.Employee;
import com.infs.kc.scott.domain.MemoryScottDB;
import com.infs.kc.scott.domain.SalaryGrades;

import static java.util.stream.Collectors.*;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class ExcersisesOfCollecting {
	
    static List<Department> departments;
    static List<Employee> employees;
    static List<SalaryGrades> salgrades;
	
	@BeforeClass
	public static void setUp() {
		MemoryScottDB memoryScottDB = new MemoryScottDB();
		memoryScottDB.initializeDB();
		departments=memoryScottDB.getDepartments();
		employees=memoryScottDB.getEmployees();
		salgrades=memoryScottDB.getSalaryGrades();
	}
	
	
	@Test
	public void groupTheEmployeesOnJob() {
		//Read the readme for extra info
	}

	@Test
	public void printJobAndEmployeesWithThisJob() {
		
		//Read the readme for extra info
	}



	@Test
	public void exploringFromDoubleToInt() throws Exception {
		int result=new Double(115.11).intValue()/100;
		assertThat(result,is(1));
	}
	
	@Test
	public void goupTheEmployeesInSalaryCategories() {
		
		//Read the readme for extra info
		//The above test is a hint on how to define the salary category.
	
	}


	@Test
	public void groupEmployeeOnSalaryCategoryAndWithinACategoryOnJob() {
		
		//Read the readme for extra info
		
	}



	@Test
	public void groupEmployeeOnSalaryCategoryAndWithinACategoryOnJobAndCountTheNumberOfMembersInEachGroup() {
		
		
		//Read the readme for extra info
	}
	
}

