package com.greatlearning.employee.controller;

import com.greatlearning.employee.entities.Employees;
import com.greatlearning.employee.entities.Roles;
import com.greatlearning.employee.entities.User;
import com.greatlearning.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
public class EmployeeController {
    private EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService theEmployeeService){
        employeeService=theEmployeeService;
    }
    @PostMapping("/user")
    public User saveUser(@RequestBody User user){
        return employeeService.saveUser(user);
    }
    @PostMapping("/role")
    public Roles saveRole(@RequestBody Roles role){
        return employeeService.saveRole(role);
    }
    @GetMapping("/employeesList")
    public List<Employees> findAll(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
        System.out.println(currentPrincipalName);
        return employeeService.findAll();
    }
    @GetMapping("/getEmployee")
    public Employees getEmployee(@PathVariable int employeeId){
        Employees theEmployee= employeeService.findById(employeeId);
        if(theEmployee==null){
            throw new RuntimeException("Employee if not found "+employeeId);
        }
        return theEmployee;
    }
    @PostMapping("/employees/add")
    public Employees addEmployee(@RequestBody Employees theEmployees){
        theEmployees.setId(0);
        employeeService.save(theEmployees);
        return theEmployees;
    }

    @PutMapping("/employees/update/{employeeId}")
    public Employees updateEmployee(@RequestBody Employees theEmployee,@PathVariable int employeeId){
        Employees tempEmployee= employeeService.findById(employeeId);
        if(tempEmployee==null){
            throw new RuntimeException("Employee id not found "+employeeId);

        }
        tempEmployee.setFirstName(theEmployee.getFirstName());
        tempEmployee.setLastName(theEmployee.getLastName());
        tempEmployee.setEmail(theEmployee.getEmail());
        employeeService.save(tempEmployee);
        theEmployee.setId(employeeId);
        return theEmployee;
    }
    @DeleteMapping("/employees/delete/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employees tempEmployee = employeeService.findById(employeeId);
        if (tempEmployee == null) {
            throw new RuntimeException("Employee id not found " + employeeId);

        }
        employeeService.deleteById(employeeId);
        return "Deleted employee id-" + employeeId;
    }
    @GetMapping("/employees/search/{firstName}")
    public List<Employees> searchByFirstName(@PathVariable String firstName){
        return employeeService.searchByFirstName(firstName);
    }
    @GetMapping("/employees/sort")
    public List<Employees> sortByFirstName(){
        return employeeService.sortByFirstNameAsc();
    }

}
