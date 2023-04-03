package com.example.learningspringboot.service;

import com.example.learningspringboot.model.Holiday;
import com.example.learningspringboot.repository.HolidaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class HolidaysService {
    private final HolidaysRepository holidaysRepository;

    @Autowired
    public HolidaysService(HolidaysRepository holidaysRepository) {
        this.holidaysRepository = holidaysRepository;
    }

    public List<Holiday> findAll() {
        Iterable<Holiday> holidays = holidaysRepository.findAll();
        List<Holiday> result = new ArrayList<Holiday>();
        holidays.forEach(result::add);
        return result;
    }
}
