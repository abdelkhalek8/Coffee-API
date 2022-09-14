package com.example.springjwt.service;

import com.example.springjwt.domain.Role;
import com.example.springjwt.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component("userService")
public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    Void addRoleToUser(String username,String roleName);
    User getUser(String email);
    List<User>getUsers();
}
