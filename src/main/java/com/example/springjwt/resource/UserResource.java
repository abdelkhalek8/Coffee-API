package com.example.springjwt.resource;

import com.example.springjwt.domain.Role;
import com.example.springjwt.domain.User;
import com.example.springjwt.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api") @RequiredArgsConstructor
public class UserResource  {
    private final UserService userService;

  @GetMapping("/users")
    public ResponseEntity<List<User>>getUser(){
        return ResponseEntity.ok().body(userService.getUsers());
    }




    @PostMapping("/user/save")
    public ResponseEntity<User> createUser(@RequestBody User user){

        return ResponseEntity.ok().body(userService.saveUser(user));

    }
    @PostMapping("/role")

    public ResponseEntity<Role> createRole(@RequestBody Role role){

        return ResponseEntity.ok().body(userService.saveRole(role));

    }
    @PostMapping("/role/user")

    public ResponseEntity<Role> addRoleToUser(@RequestBody RoleTouser role){
        userService.addRoleToUser(role.getUserName(),role.getRoleName());
        return ResponseEntity.ok().build();

    }

}
@Data
class RoleTouser{
    public String getUserName() {
        return userName;
    }

    private String userName;
    private String roleName;

    public String getRoleName() {
        return roleName;
    }
}