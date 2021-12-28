package com.greatlearning.employee.service;


import com.greatlearning.employee.entities.Employees;
import com.greatlearning.employee.entities.Roles;
import com.greatlearning.employee.entities.User;
import com.greatlearning.employee.repository.EmployeeRepository;
import com.greatlearning.employee.repository.RoleRepository;
import com.greatlearning.employee.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bcryptEncoder;
    @Override
    public List<Employees> findAll() {

        return employeeRepository.findAll();
    }

    @Override
    public Employees findById(int theId) {
        return employeeRepository.getById(theId);
    }

    @Override
    public void save(Employees theEmployee) {

        employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {

        employeeRepository.deleteById(theId);
    }

    @Override
    public List<Employees> searchByFirstName(String firstName) {
        return employeeRepository.findByFirstNameContainsAllIgnoreCase(firstName);
    }

    @Override
    public List<Employees> sortByFirstNameAsc() {
        return employeeRepository.findAllByOrderByFirstNameAsc();
    }

    @Override
    public User saveUser(User user) {

        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Roles saveRole(Roles role) {

        return roleRepository.save(role);
    }



}