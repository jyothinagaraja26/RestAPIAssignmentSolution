package com.greatlearning.employee.service;

import com.greatlearning.employee.entities.Employees;
import com.greatlearning.employee.entities.Roles;
import com.greatlearning.employee.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {


    public List<Employees> findAll();

    public Employees findById(int theId);

    public void save(Employees theEmployee);

    public void deleteById(int theId);

    public List<Employees> searchByFirstName(String firstName);

    public List<Employees> sortByFirstNameAsc();

    public User saveUser(User user);

    public Roles saveRole(Roles role);


}

