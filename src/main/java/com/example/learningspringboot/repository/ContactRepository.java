package com.example.learningspringboot.repository;

import com.example.learningspringboot.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {
    Iterable<Contact> findByStatus(String status);
}
