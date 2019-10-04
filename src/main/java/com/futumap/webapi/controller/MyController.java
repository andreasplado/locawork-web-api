package com.futumap.webapi.controller;

import com.futumap.webapi.model.City;
import com.futumap.webapi.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private ICityService cityService;

    @GetMapping("/showCities")
    public String findCities(Model model) {
        model.addAttribute("cities", (List<City>) cityService.findAll());

        return "showCities";
    }
}
