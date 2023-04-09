package com.example.learningspringboot.service;

import com.example.learningspringboot.model.EazyClass;
import com.example.learningspringboot.repository.EazyClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EazyClassService {

    @Autowired
    private EazyClassRepository eazyClassRepository;

    public void save(EazyClass eazyClass) {
        eazyClassRepository.save(eazyClass);
    }

    public List<EazyClass> findAll() {
        Iterable<EazyClass> results = eazyClassRepository.findAll();
        List<EazyClass> eazyClasses = new ArrayList<EazyClass>();
        results.forEach(eazyClasses::add);
        return eazyClasses;
    }

    public void delete(EazyClass eazyClass) {
        eazyClassRepository.delete(eazyClass);
    }

    public EazyClass findById(int id) {
        Optional<EazyClass> eazyClass = eazyClassRepository.findById(id);
        return eazyClass.orElseGet(EazyClass::new);
    }
}
