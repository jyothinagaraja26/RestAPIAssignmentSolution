package com.greatlearning.employee.repository;

import com.greatlearning.employee.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles,Integer> {
}
