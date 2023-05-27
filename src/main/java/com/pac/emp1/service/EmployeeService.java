package com.pac.emp1.service;

import com.pac.emp1.entity.Employee;
import com.pac.emp1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
   @Autowired
    private EmployeeRepository employeeRepository;
   @Autowired
   private Employee employee;

    public EmployeeService(Employee employee) {
        this.employee = employee;
    }

    public List<Employee> getAllEmployees() {
      //  return employeeRepository.findAll();
        return  employeeRepository.findAll();
    }

    public Employee getEmployeeById(String id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElse(null);
    }

   /* public Employee getByName(String name){
        Optional<Employee> employee1=employeeRepository.findByName(String name);
        return employee1.orElse(null);
    }
*/
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(String id, Employee updatedEmployee) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            Employee existingEmployee = employee.get();
            // Update the existingEmployee with the properties from updatedEmployee
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setDesignation(updatedEmployee.getDesignation());
            // Set other properties as needed
            return employeeRepository.save(existingEmployee);
        } else {
            return null; // Handle employee not found scenario
        }
    }

    public void deleteEmployee(String id) {
        employeeRepository.deleteById(id);
    }

}
