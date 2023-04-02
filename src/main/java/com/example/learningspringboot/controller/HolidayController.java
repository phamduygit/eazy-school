package com.example.learningspringboot.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.learningspringboot.service.HolidaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.learningspringboot.model.Holiday;

@Controller
public class HolidayController {

    private final HolidaysService holidaysService;

    @Autowired
    public HolidayController(HolidaysService holidaysService) {
        this.holidaysService = holidaysService;
    }

    @RequestMapping(value = "/holidays/{display}", method = RequestMethod.GET)
    public String displayHolidays(@PathVariable String display, Model model) {
        if (display != null && display.equals("all")) {
            model.addAttribute("festival", true);
            model.addAttribute("federal", true);
        } else if (display != null && display.equals("festival")) {
            model.addAttribute("festival", true);
            model.addAttribute("federal", false);
        } else if (display != null && display.equals("federal")) {
            model.addAttribute("federal", true);
            model.addAttribute("festival", false);
        }
        List<Holiday> holidays = holidaysService.findAllHolidays();
        Holiday.Type[] types = Holiday.Type.values();
        if (holidays.isEmpty()) {
            return "holidays.html";
        }
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }

        return "holidays.html";
    }    
}
