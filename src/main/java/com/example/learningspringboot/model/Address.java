package com.example.learningspringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    @NotBlank(message = "The address must not be blank")
    @Size(min = 5, message = "The length of address must be at least 5 characters long")
    private String address1;

    private String address2;

    @NotBlank(message = "City must not be blank")
    @Size(min = 5, message = "City must be at least 5 characters long")
    private String city;

    @NotBlank(message = "State must not be blank")
    @Size(min = 5, message = "State must be at least 5 characters long")
    private String state;

    @NotBlank(message = "Zip code must not be blank")
    @Size(min = 5, message = "Zip code must be at least 5 characters long")
    private String zipCode;
}
