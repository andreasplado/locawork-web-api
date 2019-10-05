package com.futumap.webapi.respository;

import com.futumap.webapi.dao.entity.CityEntity;
import com.futumap.webapi.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<CityEntity, Long> {

}