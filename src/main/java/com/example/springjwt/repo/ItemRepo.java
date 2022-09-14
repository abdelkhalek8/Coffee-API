package com.example.springjwt.repo;


import com.example.springjwt.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component("itemRepo")
public interface ItemRepo extends JpaRepository<Item, Long> {

}
