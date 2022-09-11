package com.example.springjwt.repo;

import com.example.springjwt.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {

Role findByName(String name);

}
