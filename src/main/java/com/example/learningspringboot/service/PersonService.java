package com.example.learningspringboot.service;

import com.example.learningspringboot.constants.EazySchoolConstants;
import com.example.learningspringboot.model.Person;
import com.example.learningspringboot.model.Role;
import com.example.learningspringboot.repository.PersonRepository;
import com.example.learningspringboot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Boolean createNewUser(Person person) {
        boolean isSaved = false;
        Role role = roleRepository.getByRoleName(EazySchoolConstants.STUDENT_ROLE);
        person.setRole(role);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person = personRepository.save(person);
        if (person.getPersonId() > 0)
        {
            isSaved = true;
        }
        return isSaved;
    }

    public Person findByEmail(String email) {
        Optional<Person> person = personRepository.findByEmail(email);
        return person.orElse(null);
    }

    public Boolean updatePerson(Person person) {
        boolean isSaved = false;
        Person savedPerson = personRepository.save(person);
        if (person.getPersonId() > 0 && person.getUpdatedAt() != null)
        {
            isSaved = true;
        }
        return isSaved;
    }

    public Person findById(int personId) {
        Optional<Person> person = personRepository.findById(personId);
        return person.orElseGet(Person::new);
    }
}
