package com.futumap.webapi.service;

import com.futumap.webapi.dao.entity.CityEntity;

import java.util.List;
import java.util.Optional;

public interface ICityService {

    List<CityEntity> findAll();
    CityEntity save (CityEntity cityEntity);
    CityEntity update(CityEntity cityEntity);
    void delete(Long id);
    Optional<CityEntity> findById(Long id);
    boolean exists(Long id);
}
