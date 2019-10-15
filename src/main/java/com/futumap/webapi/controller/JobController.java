package com.futumap.webapi.controller;

import com.futumap.webapi.dao.entity.JobCategoryEntity;
import com.futumap.webapi.dao.entity.JobEntity;
import com.futumap.webapi.service.JobCategoryService;
import com.futumap.webapi.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobCategoryService jobCategoryService;

    @GetMapping
    public ResponseEntity<HashMap<String,Object>> getAll() {
        List<JobEntity> jobs = jobService.findAll();
        List<JobCategoryEntity> categories = jobCategoryService.findAll();
        HashMap<String, Object> combined = new HashMap<>();

        combined.put("jobs", jobs);
        combined.put("categories", categories);

        if (combined.isEmpty()) {
            return new ResponseEntity<HashMap<String,Object>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<HashMap<String, Object>>(combined, HttpStatus.OK);
    }

    @RequestMapping(value="/getjobsbylocation", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getByRadius(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam Double distance) {
        List<JobEntity> jobs = jobService.findNearestJobs(latitude, longitude, distance);
        List<JobCategoryEntity> categories = jobCategoryService.findAll();
        List<Object> combined = new ArrayList<>();
        combined.addAll(jobs);
        combined.addAll(categories);

        if (combined.isEmpty()) {
            return new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);
        }

        System.out.println(combined.get(1));

        return new ResponseEntity<List<Object>>(combined, HttpStatus.OK);
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