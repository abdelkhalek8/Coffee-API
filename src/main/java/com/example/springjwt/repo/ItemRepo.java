package com.example.springjwt.repo;


import com.example.springjwt.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {

}
