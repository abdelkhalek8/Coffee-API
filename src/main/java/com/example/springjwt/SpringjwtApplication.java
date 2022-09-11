package com.example.springjwt;

import com.example.springjwt.domain.Role;
import com.example.springjwt.domain.User;
import com.example.springjwt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;

@SpringBootApplication
public class SpringjwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringjwtApplication.class,args);
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
/*
  @Bean
    CommandLineRunner run(UserService userService){
        return args ->{
            userService.saveUser(new User(NULL,"ali","123","ali",new ArrayList<>()));
            userService.saveUser(new User(NULL,"ahmed","123","ahmed",new ArrayList<>()));
            userService.saveUser(new User(NULL,"omar","123","omar",new ArrayList<>()));


            userService.saveRole(new Role(NULL,"user"));
            userService.saveRole(new Role(NULL,"admin"));

            userService.addRoleToUser("ali","user");
            userService.addRoleToUser("ahmed","user");
            userService.addRoleToUser("omar","admin");
            userService.addRoleToUser("omar","user");

        };




  }
*/

}
