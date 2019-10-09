package com.futumap.webapi.controller;

import com.futumap.webapi.dao.entity.JobEntity;
import com.futumap.webapi.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public ResponseEntity<List<JobEntity>> getAll() {
        List<JobEntity> cities = jobService.findAll();

        if (cities == null || cities.isEmpty()) {
            return new ResponseEntity<List<JobEntity>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<JobEntity>>(cities, HttpStatus.OK);
    }

    @RequestMapping(value="/getjobsbylocation", method = RequestMethod.GET)
    public ResponseEntity<List<JobEntity>> getByRadius(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam Double distance) {
        List<JobEntity> jobs = jobService.findNearestJobs(latitude, longitude, distance);

        if (jobs == null || jobs.isEmpty()) {
            return new ResponseEntity<List<JobEntity>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<JobEntity>>(jobs, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<JobEntity> get(@PathVariable("id") Integer id) {

        if (!jobService.exists(id)) {
            return new ResponseEntity<JobEntity>(HttpStatus.NOT_FOUND);
        }

        Optional<JobEntity> cityEntity = jobService.findById(id);
        return cityEntity.map(entity -> new ResponseEntity<>(entity, HttpStatus.OK)).orElse(null);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody JobEntity city, UriComponentsBuilder ucBuilder) {
        jobService.save(city);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/city/{id}").buildAndExpand(city.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<JobEntity> update(@PathVariable Integer id, @RequestBody JobEntity jobEntity) {

        if (!jobService.exists(id)) {
            return new ResponseEntity<JobEntity>(HttpStatus.NOT_FOUND);
        }
        jobService.update(jobEntity);
        return new ResponseEntity<JobEntity>(jobEntity, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        Optional<JobEntity> cityEntity = jobService.findById(id);

        if (!cityEntity.isPresent()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        jobService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}