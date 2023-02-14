package jp.co.axa.apidemo.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import jp.co.axa.apidemo.entities.Employee;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {
    
    @MockBean
	EmployeeService service;

    @Captor
    private ArgumentCaptor<Employee> employeCaptor;

    @Before 
    public void init() {

	}

	@Test
	public void testSaveEmployee() {
		Employee employee = new Employee(1, "Emp1", 10000, "IT");
		// check saved data with pre-set data
		when(service.saveEmployee(employee)).thenReturn(employee);
		Employee employeeCheck = service.saveEmployee(employee);
		assertEquals(employee.getName(), employeeCheck.getName());
		assertEquals(employee.getId(), employeeCheck.getId());
		assertEquals(employee.getDepartment(), employeeCheck.getDepartment());
	}

    @Test
    public void testUpdateEmployee() {
        Employee employee = new Employee(1, "Emp1", 10000, "IT");
        // check updated data with new-set data
        employee.setName("Emp2");
        when(service.updateEmployee(employee)).thenReturn(employee);
        Employee employeeCheck = service.updateEmployee(employee);
        assertEquals(employee.getName(), employeeCheck.getName());
    }
}
