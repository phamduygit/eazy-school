package com.example.learningspringboot.service;

import com.example.learningspringboot.model.Course;
import com.example.learningspringboot.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursesService {

    @Autowired
    private CoursesRepository coursesRepository;

    public List<Course> findAll() {
        return coursesRepository.findAll();
    }

    public boolean save(Course course) {
        boolean isSaved = false;
        Course savedCourse = coursesRepository.save(course);
        if (savedCourse.getCourseId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    public Course findById(int courseId) {
        Optional<Course> course = coursesRepository.findById(courseId);
        return course.orElse(null);
    }
}
