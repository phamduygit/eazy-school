package com.example.learningspringboot.repository;

import com.example.learningspringboot.model.EazyClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EazyClassRepository extends CrudRepository<EazyClass, Integer> {

}
