package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class User {

    @Id  @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    //    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
//    @Email(message = "Email should be valid")
//    @NotNull(message = "Name should not be null")
    String name;

        @Min(value = 1, message = "min should be 1")
    @Max(value = 120, message = "max should be 120")
//    @NotNull(message = "Age should not be null")
    int age;
}
