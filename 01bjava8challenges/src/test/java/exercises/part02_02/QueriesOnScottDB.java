package exercises.part02_02;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.infs.kc.scott.domain.Department;
import com.infs.kc.scott.domain.Employee;
import com.infs.kc.scott.domain.MemoryScottDB;
import com.infs.kc.scott.domain.SalaryGrades;

import static java.util.stream.Collectors.toList;

public class QueriesOnScottDB {
	
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
	public void showAllEmployees() {
		// TODO call  forEach employee the toString method
		// and familiarize yourself with the data and the structure of it
		
	}

	@Test
	public void showAllDepartments() {
		// TODO call  forEach department the toString method
		// and familiarize yourself with the data and the structure of it
	}
	
	@Test
	public void showAllSalgrades() {
		// TODO call  forEach salgrades the toString method
		// and familiarize yourself with the data and the structure of it
	}

	@Test
	public void printAllEmployeesWithJobIsClerk() {
		//TODO implement the code to get the job done
	}

	@Test
	public void printAllEmployeesWithJobIsClerkAndSortOnSalaryAscending() {
		//TODO implement the code to get the job done
		//sorted expects a Comparator
	}
	
	@Test
	public void printAllEmployeesWithJobIsClerkAndSortOnSalaryDecending() {
		//TODO implement the code to get the job done
	}
	
	@Test
	public void printAllUniqueJobsHoldedByEmployees() {
		//TODO implement the code to get the job done
	}
	
	@Test
	public void printAllEmployeesWorkingOnDepartment10SortedByName() {
		//TODO implement the code to get the job done
	}
	
	@Test
	public void generatedOneStringContainingAllTheSortedNamesOfTheEmployees() {
		//TODO implement the code to get the job done
		         
	}
	
	@Test
	public void areThereAnyEmployeesBasesInNewYork() {
		//TODO implement the code to get the job done
	}
	
	@Test
	public void printTheHighestSalary() {
		//TODO implement the code to get the job done
	}


	@Test
	public void printTheLowestSalary() {
		//TODO implement the code to get the job done
	}

	@Test
	public void printTheHighestCommission() {
		//TODO implement the code to get the job done
		//Look at the domain, what are valid values?
	}
	
	@Test
	public void printAllEmployeesHavingASalaryInSalaryGrade2() {
		//TODO implement the code to get the job done
		//This is a tough one!
		
	}
	
	class EmployeeSalaryGrades {
		Employee employee;
		SalaryGrades salaryGrade;
		
		public EmployeeSalaryGrades(Employee employee, SalaryGrades salaryGrade) {
			super();
			this.employee = employee;
			this.salaryGrade = salaryGrade;
		}

		@Override
		public String toString() {
			return "EmployeeSalaryScale [employee=" + employee
					+ ", salaryGrade=" + salaryGrade + "]";
		}
		 
	}
	
	@Test
	public void printAllEmployeesHavingASalaryInSalaryGrade2SecondAttempt() {
		//TODO implement the code to get the job done
		//This is still a tough one, but should be better to digest when
		//using the special purpose class EmployeeSalaryGrade
	}
	
	@Test
	public void howManyEmployeesAreHavingASalaryInSalaryGrade2Or3() {
		//TODO implement the code to get the job done
		
	}
}

