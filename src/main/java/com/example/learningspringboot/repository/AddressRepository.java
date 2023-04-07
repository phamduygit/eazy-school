package com.example.learningspringboot.repository;

import com.example.learningspringboot.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
