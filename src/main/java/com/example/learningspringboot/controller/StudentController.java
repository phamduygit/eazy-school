package com.example.learningspringboot.controller;

import com.example.learningspringboot.model.Person;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("student")
public class StudentController {

    @RequestMapping(value = "/displayCourses", method = RequestMethod.GET)
    private String displayCourses(Model model, HttpSession httpSession) {
        Person person = (Person) httpSession.getAttribute("personObject");
        model.addAttribute("person", person);
        return "courses_enrolled.html";
    }
}
