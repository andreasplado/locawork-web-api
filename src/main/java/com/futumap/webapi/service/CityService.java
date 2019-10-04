package com.futumap.webapi.service;

import com.futumap.webapi.model.City;
import com.futumap.webapi.respository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService implements ICityService {

    @Autowired
    private CityRepository repository;

    @Override
    public List<City> findAll() {
        return (List<City>) repository.findAll();
    }
}