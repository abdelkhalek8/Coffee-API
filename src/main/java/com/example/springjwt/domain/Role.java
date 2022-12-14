package com.example.springjwt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@Component("role")
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    private String name;



}
