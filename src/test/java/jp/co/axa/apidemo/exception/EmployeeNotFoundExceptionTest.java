package jp.co.axa.apidemo.exception;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.axa.apidemo.services.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
public class EmployeeNotFoundExceptionTest {
    
	@MockBean
	EmployeeServiceImpl employeeServiceImpl;

    @Test
    public void checkEmplyeeNotFoundException() {
		// check Employee Not Found Exception
		Long id = 326326L;
		try {
			when(employeeServiceImpl.getEmployee(id)).thenThrow(new EmployeeNotFoundException(id));
			employeeServiceImpl.getEmployee(id);
		} catch (Exception e) {
			assertEquals(EmployeeNotFoundException.class, e.getClass());
			assertEquals("Could not find targeted employee. Employee Id :" + id, e.getMessage());
		}
	}
}
