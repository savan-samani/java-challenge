package jp.co.axa.apidemo.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.axa.apidemo.entities.Employee;

@RunWith(SpringRunner.class)
public class EmployeeRepositoryTest {

    @MockBean
	private EmployeeRepository employeeRepository;

    @Test
	public void checkSaveEmplyeeRepo() {
        // check repo save method
		Employee employee = new Employee(1, "Emp1", 1000, "ENTC");
        when(employeeRepository.save(employee)).thenReturn(employee);
		Employee employeeCheck = employeeRepository.save(employee);
		assertNotNull(employeeCheck);
		assertEquals(employee.getName(), employeeCheck.getName());
	}
}
