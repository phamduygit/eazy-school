package com.example.learningspringboot.controller;

import com.example.learningspringboot.model.Address;
import com.example.learningspringboot.model.Person;
import com.example.learningspringboot.model.Profile;
import com.example.learningspringboot.service.PersonService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/displayProfile", method = RequestMethod.GET)
    public ModelAndView displayProfilePage(Model model, HttpSession httpSession) {
        Person person = (Person) httpSession.getAttribute("personObject");
        Profile profile = new Profile();
        profile.setName(person.getName());
        profile.setEmail(person.getEmail());
        profile.setMobileNumber(person.getMobileNumber());
        if (person.getAddress() != null && person.getAddress().getAddressId() > 0) {
            profile.setAddress1(person.getAddress().getAddress1());
            profile.setAddress2(person.getAddress().getAddress2());
            profile.setCity(person.getAddress().getCity());
            profile.setState(person.getAddress().getState());
            profile.setZipCode(person.getAddress().getZipCode());
        }
        ModelAndView modelAndView = new ModelAndView("profile.html");
        modelAndView.addObject(profile);
        return modelAndView;
    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors, HttpSession httpSession) {
        if (errors.hasErrors()) {
            return "profile.html";
        }
        Person person = (Person)httpSession.getAttribute("personObject");
        person.setName(profile.getName());
        person.setEmail(profile.getEmail());
        person.setMobileNumber(profile.getMobileNumber());
        if (person.getAddress() == null || !(person.getAddress().getAddressId() > 0)) {
            person.setAddress(new Address());
        }
        person.getAddress().setAddress1(profile.getAddress1());
        person.getAddress().setAddress2(profile.getAddress2());
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setState(profile.getState());
        person.getAddress().setZipCode(profile.getZipCode());
        personService.save(person);
        return "redirect:/displayProfile";
    }

}
