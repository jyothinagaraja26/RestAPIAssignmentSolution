package com.greatlearning.employee.repository;

import com.greatlearning.employee.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

public User getUserByUsername(String username);
}
