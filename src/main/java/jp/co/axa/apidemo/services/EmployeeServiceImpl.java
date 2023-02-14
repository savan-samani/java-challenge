package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    public Optional<Employee> getEmployee(Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (!optionalEmployee.isPresent()) {
            logger.error("Employee Not Found. Employee Id :{}", employeeId);
            throw new EmployeeNotFoundException(employeeId);
        }
        return employeeRepository.findById(employeeId);
    }

    public Employee saveEmployee(Employee employee){
        try {
            if (employee == null) {
                logger.warn("Please pass valid employee information");
                return null;
            } else {
                return employeeRepository.save(employee);
            }
        } catch (Exception e) {
            logger.error("Error occurred while saving employee information.", e);
            return null;
        }
    }

    public void deleteEmployee(Long employeeId){
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (!optionalEmployee.isPresent()) {
            logger.error("Employee Not Found. Employee Id :{}", employeeId);
            throw new EmployeeNotFoundException(employeeId);
        }
        try {
            employeeRepository.deleteById(employeeId);
        } catch (Exception e) {
            logger.error("Error occurred while deleting employee information.");
        }
    }

    public Employee updateEmployee(Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());
        if (!optionalEmployee.isPresent()) {
            logger.error("Employee Not Found. Employee Id :{}", employee.getId());
            throw new EmployeeNotFoundException(employee.getId());
        }
        try {
            employee = employeeRepository.save(employee);
        } catch (Exception e) {
            logger.error("Error occurred while updating employee information.");
        }
        return employee;
    }
}