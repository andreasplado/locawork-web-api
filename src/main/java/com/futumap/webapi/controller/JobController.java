package com.futumap.webapi.controller;

import com.futumap.webapi.dao.entity.JobCategoryEntity;
import com.futumap.webapi.dao.entity.JobEntity;
import com.futumap.webapi.service.JobCategoryService;
import com.futumap.webapi.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private static String KEY_JOBS = "jobs";
    private static String KEY_CATEGORIES = "categories";

    @Autowired
    private JobService jobService;

    @Autowired
    private JobCategoryService jobCategoryService;

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> getAll() {
        List<JobEntity> jobs = jobService.findAll();
        List<JobCategoryEntity> categories = jobCategoryService.findAll();
        HashMap<String, Object> combined = new HashMap<>();

        combined.put(KEY_JOBS, jobs);
        combined.put(KEY_CATEGORIES, categories);

        if (combined.isEmpty()) {
            return new ResponseEntity<HashMap<String, Object>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<HashMap<String, Object>>(combined, HttpStatus.OK);
    }

    @RequestMapping(value = "/getjobsbylocation", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getUserOffers(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam Double distance, @RequestParam String googleAccountId) {
        List<JobEntity> jobs = jobService.findOtherUsersNearestJobs(latitude, longitude, distance, googleAccountId);
        List<JobCategoryEntity> categories = jobCategoryService.findAll();
        HashMap<String, Object> combined = new HashMap<>();
        combined.put(KEY_JOBS, jobs);
        combined.put(KEY_CATEGORIES, categories);

        if (combined.isEmpty()) {
            return new ResponseEntity<HashMap<String, Object>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<HashMap<String, Object>>(combined, HttpStatus.OK);
    }

    @RequestMapping(value = "/getalljobsbylocation", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAllJobsByLocation(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam Double distance) {
        List<JobEntity> jobs = jobService.findAllNearestJobs(latitude, longitude, distance);
        List<JobCategoryEntity> categories = jobCategoryService.findAll();
        HashMap<String, Object> combined = new HashMap<>();
        combined.put(KEY_JOBS, jobs);
        combined.put(KEY_CATEGORIES, categories);

        if (combined.isEmpty()) {
            return new ResponseEntity<HashMap<String, Object>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<HashMap<String, Object>>(combined, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<JobEntity> get(@PathVariable("id") Integer id) {

        if (!jobService.exists(id)) {
            return new ResponseEntity<JobEntity>(HttpStatus.NOT_FOUND);
        }

        Optional<JobEntity> cityEntity = jobService.findById(id);
        return cityEntity.map(entity -> new ResponseEntity<>(entity, HttpStatus.OK)).orElse(null);
    }

    @RequestMapping(value = "/getjobsbygoogleaccount", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAll(@RequestParam String googleAccountEmail) {
        List<JobEntity> jobs = jobService.findAllCurrentUserJobs(googleAccountEmail);
        HashMap<String, Object> combined = new HashMap<>();
        combined.put(KEY_JOBS, jobs);
        combined.put(KEY_CATEGORIES, jobs);

        if (combined.isEmpty()) {
            return new ResponseEntity<HashMap<String, Object>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<HashMap<String, Object>>(combined, HttpStatus.OK);
    }

    @RequestMapping(value = "/getappliedjobsbygoogleaccount", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAppliedJobsByGooogleAccount(@RequestParam String googleAccountEmail) {
        List<JobEntity> jobs = jobService.findAppliedJobs(googleAccountEmail);
        HashMap<String, Object> combined = new HashMap<>();
        combined.put(KEY_JOBS, jobs);
        combined.put(KEY_CATEGORIES, jobs);

        if (combined.isEmpty()) {
            return new ResponseEntity<HashMap<String, Object>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<HashMap<String, Object>>(combined, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public JobEntity create(@RequestBody JobEntity job, UriComponentsBuilder ucBuilder) {
        jobService.save(job);
        return job;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<JobEntity> update(@PathVariable Integer id, @RequestBody JobEntity jobEntity) {

        if (jobService.exists(id)) {
            jobService.update(jobEntity);
            return new ResponseEntity<JobEntity>(jobEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<JobEntity>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "registerjob/{id}", method = RequestMethod.PUT)
    public ResponseEntity<JobEntity> registerjob(@PathVariable("id") Integer id, @RequestBody JobEntity jobEntity) {

        if (jobService.exists(id)) {
            jobService.update(jobEntity);
            return new ResponseEntity<JobEntity>(jobEntity, HttpStatus.OK);

        } else {
            return new ResponseEntity<JobEntity>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<JobEntity> delete(@PathVariable("id") Integer id) {

        if (!jobService.exists(id)) {
            return new ResponseEntity<JobEntity>(HttpStatus.OK);
        }else {
            jobService.delete(id);
            return new ResponseEntity<JobEntity>(HttpStatus.OK);
        }


    }

    @RequestMapping(value = "deleteall", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteaLL() {
        jobService.deleteAll();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}