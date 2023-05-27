package com.pac.emp1.configure;

import com.pac.emp1.entity.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Employee employeeBean(){
    return  new Employee();
    }
}
