package com.pac.emp1.controller;

import com.pac.emp1.entity.Employee;
import com.pac.emp1.repository.EmployeeRepository;
import com.pac.emp1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/getall")
    public List<Employee> getAllEmployees() {
        System.out.println("hi");
        // Retrieve all employees using employeeService or employeeRepository
        return employeeService.getAllEmployees();
        // or
        // return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable String id) {
        // Retrieve the employee by ID using employeeService or employeeRepository
         return employeeService.getEmployeeById(id);
        // or
        // return employeeRepository.findById(id).orElse(null);
    }
  /*  @GetMapping("/emp/{name}")
    public Employee getEmployeeByName(@PathVariable String name){
        System.out.println("you are inside GetEmployeeByName");
        return employeeService.getByName(String name);
    }*/

    @PostMapping("/create")
    public ResponseEntity<Object> createEmployee(@RequestBody Employee employee) {
        // Create the employee using employeeService or employeeRepository
         Employee createdEmployee=employeeService.createEmployee(employee);
         if(createdEmployee != null && createdEmployee.getDesignation() != null) {
             // or
             // return employeeRe
             // pository.save(employee);
             return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
         }
         else {
           //  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create employee");
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create employee");
         }

    }
   @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable String id, @RequestBody Employee updatedEmployee) {
        // Update the employee with the given ID using employeeService or employeeRepository
       // return employeeService.updateEmployee(id, updatedEmployee);
        // or
         Employee existingEmployee = employeeRepository.findById(id).orElse(null);
         if (existingEmployee != null) {
        //     // Update the existingEmployee with the properties from updatedEmployee
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setDesignation(updatedEmployee.getDesignation());
        //     // Set other properties as needed
             return employeeRepository.save(existingEmployee);
         } else {
             return null; // Handle employee not found scenario
        }
    }
    //@DeleteMapping("/{id}")
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable String id) {
        // Delete the employee with the given ID using employeeService or employeeRepository
        // employeeService.deleteEmployee(id);
        // or
         employeeRepository.deleteById(id);
    }

@DeleteMapping("/delete/{name}")
    public String deleteEmployeebyName(@PathVariable("name") String name){

            // Delete employee by name logic here

             employeeRepository.deleteByName(name);
            return "Employee deleted: " + name;

      /*  else {
            return "Employee name should not be empty"+name;
        }*/
  }


}
