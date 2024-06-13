package com.asha.employeeService1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.asha.employeeService1.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Configuration
public class PersistenceConfiguration {

	@PersistenceContext
    private EntityManager entityManager;

    @Bean
    public SimpleJpaRepository<Employee, Long> employeeSimpleRepository() {
        return new SimpleJpaRepository<>(Employee.class, entityManager);
    }
 
}
