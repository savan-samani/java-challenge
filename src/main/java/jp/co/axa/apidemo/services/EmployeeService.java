package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;

/**
 * Interface to handle all CRUD operations of employee
 */
public interface EmployeeService {

    /**
     * Method to get all employees
     * @return List of all employees
     */
    public List<Employee> retrieveEmployees();

    /**
     * Method to get all details of particular employee
     *
     * @param employeeId
     * @return particular employee details (id, name, salary, department)
     */
    @Cacheable("employees")
    public Optional<Employee> getEmployee(Long employeeId);

    /**
     * Method to save details (id, name, salary, department) of new Employee to DB
     * @param employee
     */
    public Employee saveEmployee(Employee employee);

    /**
     * Method to delete targeted employee data from DB
     * @param employeeId
     */
    public void deleteEmployee(Long employeeId);

    /**
     * Method to delete employee details ()
     * @param employee
     */
    public Employee updateEmployee(Employee employee);
}