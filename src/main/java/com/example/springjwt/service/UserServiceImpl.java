package com.example.springjwt.service;

import com.example.springjwt.domain.Role;
import com.example.springjwt.domain.User;
import com.example.springjwt.repo.RoleRepo;
import com.example.springjwt.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements  UserService , UserDetailsService {
    private final UserRepo userRepo;
    private  final RoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;
    @Override
    public User saveUser(User user) {
try{
    log.info("saving user {} in db",user.getName());


    user.setPassword(passwordEncoder.encode(user.getPassword()));
    User use= userRepo.findByEmail(user.getEmail());
    log.info("saving user {} in 34",use);

    if(use==null){
        return userRepo.save(user);
    }
    else{
        return null;

    }
}catch (Exception e){
    return null;

}



    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public Void addRoleToUser(String username, String roleName) {
       User user =userRepo.findByEmail(username);
       Role role=roleRepo.findByName( roleName);
       user.getRole().add(role);

        return null;
    }

    @Override
    public User getUser(String userName) {
        return userRepo.findByEmail(userName);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      User user=userRepo.findByEmail(email);
      if (user==null){
          log.error("no user found");
          throw new UsernameNotFoundException("user not found");
      } else{
          log.info("user found",email);
      }
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
       user.getRole().forEach(role->{
           authorities.add(new SimpleGrantedAuthority(role.getName()));
       });
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),authorities);
    }
}
