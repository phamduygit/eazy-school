package com.example.learningspringboot.repository;

import com.example.learningspringboot.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    Person readByEmail(String email);
}
