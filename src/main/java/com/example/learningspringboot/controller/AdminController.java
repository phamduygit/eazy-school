package com.example.learningspringboot.controller;

import com.example.learningspringboot.model.Course;
import com.example.learningspringboot.model.EazyClass;
import com.example.learningspringboot.model.Person;
import com.example.learningspringboot.service.CoursesService;
import com.example.learningspringboot.service.EazyClassService;
import com.example.learningspringboot.service.PersonService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "admin")
public class AdminController {

    @Autowired
    private EazyClassService eazyClassService;

    @Autowired
    private PersonService personService;

    @Autowired
    private CoursesService coursesService;

    @RequestMapping(value = "/displayClasses", method = RequestMethod.GET)
    public ModelAndView displayClassesPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("classes.html");
        List<EazyClass> eazyClasses = eazyClassService.findAll();
        modelAndView.addObject("eazyClasses", eazyClasses);
        modelAndView.addObject("eazyClass", new EazyClass());
        return modelAndView;
    }


    @RequestMapping(value = "/addNewClass", method = RequestMethod.POST)
    public String addNewClass(@Valid @ModelAttribute("eazyClass") EazyClass eazyClass) {
        eazyClassService.save(eazyClass);
        return "redirect:/admin/displayClasses";
    }


    @RequestMapping(value = "/deleteClass", method = RequestMethod.GET)
    public String deleteClass(@RequestParam int id, Model model) {
        EazyClass eazyClass = eazyClassService.findById(id);
        for (Person person : eazyClass.getPersons()) {
            person.setEazyClass(null);
            personService.updatePerson(person);
        }
        eazyClassService.delete(eazyClass);
        return "redirect:/admin/displayClasses";
    }

    @RequestMapping(value = "/displayStudents", method = RequestMethod.GET)
    public String displayStudents(@RequestParam int classId, Model model, HttpSession httpSession, @RequestParam(required = false) String error) {
        String errorMessage = "";
        if (error != null) {
            errorMessage = "You need create an account with this email!";
            model.addAttribute("errorMessage", errorMessage);
        }
        EazyClass eazyClass = eazyClassService.findById(classId);
        model.addAttribute("eazyClass", eazyClass);
        model.addAttribute("person",new Person());
        httpSession.setAttribute("eazyClassObject", eazyClass);
        return "students.html";
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("person") Person person, HttpSession httpSession) {
        EazyClass eazyClass = (EazyClass) httpSession.getAttribute("eazyClassObject");
        Person personEntity = personService.findByEmail(person.getEmail());
        if (personEntity == null || !(personEntity.getPersonId() > 0)) {
            return "redirect:/admin/displayStudents?classId=" + eazyClass.getClassId() + "&error=true";
        }
        personEntity.setEazyClass(eazyClass);
        personService.updatePerson(personEntity);
        eazyClass.getPersons().add(personEntity);
        eazyClassService.save(eazyClass);
        return "redirect:/admin/displayStudents?classId="+eazyClass.getClassId();
    }

    @RequestMapping(value = "/deleteStudent", method = RequestMethod.GET)
    public String deleteStudent(@RequestParam int personId, HttpSession httpSession) {
        EazyClass eazyClass = (EazyClass) httpSession.getAttribute("eazyClassObject");
        Person personEntity = personService.findById(personId);
        eazyClass.getPersons().remove(personEntity);
        eazyClassService.save(eazyClass);
        personEntity.setEazyClass(null);
        personService.updatePerson(personEntity);
        httpSession.setAttribute("eazyClassObject", eazyClass);
        return "redirect:/admin/displayStudents?classId=" + eazyClass.getClassId();
    }

    @RequestMapping(value = "/displayCourses", method = RequestMethod.GET)
    public String displayCourses(Model model) {
        List<Course> courses = coursesService.findAll();
        model.addAttribute("courses", courses);
        model.addAttribute("course", new Course());
        return "courses_secure.html";
    }

    @RequestMapping(value = "/addNewCourse", method = RequestMethod.POST)
    public String addCourses(Model model, @ModelAttribute("course") Course course) {
        coursesService.save(course);
        return "redirect:/admin/displayCourses";
    }

    @RequestMapping(value = "/viewStudents")
    public String viewStudents(@RequestParam int id, Model model) {
        Course course = coursesService.findById(id);
        model.addAttribute("courses", course);
        model.addAttribute("person", new Person());
        return "course_students.html";
    }
}
