package com.example.learningspringboot.repository;

import com.example.learningspringboot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role getByRoleName(String studentRole);
}
