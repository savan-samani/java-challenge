package jp.co.axa.apidemo.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import lombok.var;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
    
    @Autowired 
    private MockMvc mockMvc;

    @MockBean 
    EmployeeService employeeService;

	@Before
    public void init() {

	}

    // check controller getEmployees method 
    @Test
    public void checkGetEmployees() throws Exception {
        
        var employeeList = new ArrayList<Employee>(); 
        employeeList.add(new Employee(1, "Emp1", 15000, "IT1"));
        employeeList.add(new Employee(2, "Emp2", 20000, "IT2"));

        when(employeeService.retrieveEmployees()).thenReturn(employeeList);

        mockMvc.perform(get("/api/v1/employees")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].id").value(1))
				.andExpect(jsonPath("$.[0].name").value("Emp1"))
				.andExpect(jsonPath("$.[0].salary").value(15000))
				.andExpect(jsonPath("$.[0].department").value("IT1"))
				.andExpect(jsonPath("$.[1].id").value(2))
				.andExpect(jsonPath("$.[1].name").value("Emp2"))
				.andExpect(jsonPath("$.[1].salary").value(20000))
				.andExpect(jsonPath("$.[1].department").value("IT2"));
    }

    // check controller getEmployees(employeeId) method 
    @Test
    public void checkGetTargetedEmployee() throws Exception {
        final Long employeeId = 1L;
        Employee employee = new Employee(employeeId, "Emp1", 15000, "IT1");
        when(employeeService.getEmployee(employeeId)).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/api/v1/employees/{employeeId}", employeeId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Emp1"))
                .andExpect(jsonPath("$.salary").value(15000))
                .andExpect(jsonPath("$.department").value("IT1"));
    }

    @Test
	public void checkEmployeeReturn() throws Exception {

		when(employeeService.getEmployee(anyLong())).thenReturn(Optional.empty());
		mockMvc.perform(get("/api/v1/employees/100")).andDo(print())
            .andExpect(status().isNotFound());
	}

    // check controller saveEmployee(employeeId) method 
    @Test 
    public void checkEmployeeSave() throws Exception {
        Employee employee = new Employee(1, "Emp1", 15000, "IT1");
        when(employeeService.saveEmployee(employee)).thenReturn(employee);

        mockMvc.perform(post("/api/v1/employees")).andExpect(status().isCreated());
    }

    // check controller deleteEmployee(employeeId) method 
    @Test 
    public void checkEmployeeDelete() throws Exception {
        mockMvc.perform(delete("/api/v1/employees/100")).andExpect(status().isOk());
    }
}
