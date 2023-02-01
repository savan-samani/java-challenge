package jp.co.axa.apidemo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ApiDemoApplicationTests {

	@Autowired
	EmployeeService service;

	@Before
	public void contextLoads() {
	}

	@Test
	public void checkEmployeeNotFoundException() {
		Long id = 326326L;
		try {
			service.getEmployee(id);

		} catch (Exception e) {
			assertEquals(EmployeeNotFoundException.class, e.getClass());
			assertEquals("Could not find targeted employee. Employee Id :" + id, e.getMessage());
		}
	}

	@Test
	public void testSaveEmployee() {
		// Employee employee = new Employee(1, "Savan", 10000, "IT");
		// check saved data with pre-set data
		// when(service.saveEmployee(employee)).thenReturn(employee);
		// Employee employeeCheck = service.saveEmployee(employee);
		// assertEquals(employee.getName(), employeeCheck.getName());
		// assertEquals(employee.getId(), employeeCheck.getId());
		// assertEquals(employee.getDepartment(), employeeCheck.getDepartment());

	}

}
