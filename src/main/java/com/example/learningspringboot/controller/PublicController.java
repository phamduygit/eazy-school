package com.example.learningspringboot.controller;

import com.example.learningspringboot.model.Person;
import com.example.learningspringboot.service.PersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("public")
@Slf4j
public class PublicController {
    private final PersonService personService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PublicController(PersonService personService) {
        this.personService = personService;
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String displayRegisterPage(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "register.html";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String savePerson(@Valid @ModelAttribute("person") Person person, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "register.html";
        }
        boolean isSaved = personService.createNewUser(person);
        if (isSaved) {
            return "redirect:/login?register=true";
        } else {
            return "register.html";
        }
    }
}
