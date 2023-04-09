package com.example.learningspringboot.repository;

import com.example.learningspringboot.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<Course, Integer> {

}
