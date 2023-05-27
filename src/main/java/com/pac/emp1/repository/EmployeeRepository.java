package com.pac.emp1.repository;

import com.pac.emp1.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {


    public String deleteByName(String name);
    public String findByName(String name);
}
