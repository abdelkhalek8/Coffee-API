package com.example.springjwt.repo;

import com.example.springjwt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component("userRepo")
public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String Username);

}
