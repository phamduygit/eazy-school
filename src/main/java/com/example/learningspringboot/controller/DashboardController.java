package com.example.learningspringboot.controller;

import com.example.learningspringboot.model.Person;
import com.example.learningspringboot.service.PersonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {
    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/dashboard", method = {RequestMethod.GET})
    public String displayDashboardPage(Authentication authentication, Model model, HttpSession httpSession) {
        Person person = personService.findByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        if (null != person.getEazyClass() && null != person.getEazyClass().getName()) {
            model.addAttribute("enrolledClass", person.getEazyClass().getName());
        }
        httpSession.setAttribute("personObject", person);
        return "dashboard.html";
    }
}
