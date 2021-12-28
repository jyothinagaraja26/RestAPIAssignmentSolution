package com.greatlearning.employee.repository;

import com.greatlearning.employee.entities.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employees,Integer> {

    List<Employees> findByFirstNameContainsAllIgnoreCase(String firstName);
    List<Employees> findAllByOrderByFirstNameAsc();
}
