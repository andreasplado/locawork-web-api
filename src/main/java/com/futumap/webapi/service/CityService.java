package com.futumap.webapi.service;

import com.futumap.webapi.dao.entity.CityEntity;
import com.futumap.webapi.respository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService implements ICityService {

    @Autowired
    private CityRepository repository;

    @Override
    public List<CityEntity> findAll() {
        return (List<CityEntity>) repository.findAll();
    }

    @Override
    public CityEntity save(CityEntity cityEntity) {
        return repository.save(cityEntity);
    }


    @Override
    public CityEntity update(CityEntity cityEntity) {
        if(!repository.existsById(cityEntity.getId())){
            repository.save(cityEntity);
        }
        return cityEntity;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<CityEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean exists(Long id) {
        return repository.existsById(id);
    }

}