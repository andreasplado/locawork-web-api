package com.futumap.webapi.respository;

import com.futumap.webapi.dao.entity.CityEntity;
import com.futumap.webapi.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

}