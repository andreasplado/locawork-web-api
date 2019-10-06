package com.futumap.webapi.controller;

import com.futumap.webapi.dao.entity.CityEntity;
import com.futumap.webapi.service.CityService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CityEntity>> getAll() {
        List<CityEntity> cities = cityService.findAll();

        if (cities == null || cities.isEmpty()) {
            return new ResponseEntity<List<CityEntity>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<CityEntity>>(cities, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<CityEntity> get(@PathVariable("id") Long id) {

        if (!cityService.exists(id)) {
            return new ResponseEntity<CityEntity>(HttpStatus.NOT_FOUND);
        }

        Optional<CityEntity> cityEntity = cityService.findById(id);
        return cityEntity.map(entity -> new ResponseEntity<>(entity, HttpStatus.OK)).orElse(null);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody CityEntity city, UriComponentsBuilder ucBuilder) {
        cityService.save(city);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/city/{id}").buildAndExpand(city.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<CityEntity> update(@PathVariable BigInteger id, @RequestBody CityEntity cityEntity) {

        /*if (cityService.exists((id)) {
            return new ResponseEntity<CityEntity>(HttpStatus.NOT_FOUND);
        }*/
        cityService.update(cityService.update(cityEntity));
        return new ResponseEntity<CityEntity>(cityEntity, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional<CityEntity> cityEntity = cityService.findById(id);

        if (!cityEntity.isPresent()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        cityService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}