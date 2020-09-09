package com.futumap.webapi.controller;

import com.futumap.webapi.dao.entity.JobCategoryEntity;
import com.futumap.webapi.dao.entity.JobEntity;
import com.futumap.webapi.model.ResponseModel;
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
    public ResponseEntity<?> getUserOffers(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam Double distance, @RequestParam String email) {
        List<JobEntity> jobs = jobService.findOtherUsersNearestJobs(latitude, longitude, distance, email);
        List<JobCategoryEntity> categories = jobCategoryService.findAll();
        HashMap<String, Object> combined = new HashMap<>();
        combined.put(KEY_JOBS, jobs);
        combined.put(KEY_CATEGORIES, categories);

        if (combined.isEmpty()) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("You have no jobs found");
            return ResponseEntity.ok(responseModel);
        }

        return ResponseEntity.ok(combined);
    }

    @RequestMapping(value = "/getalljobsbylocation", method = RequestMethod.GET)
    public ResponseEntity<?> getAllJobsByLocation(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam Double distance) {
        List<JobEntity> jobs = jobService.findAllNearestJobs(latitude, longitude, distance);
        List<JobCategoryEntity> categories = jobCategoryService.findAll();
        HashMap<String, Object> combined = new HashMap<>();
        combined.put(KEY_JOBS, jobs);
        combined.put(KEY_CATEGORIES, categories);

        if (combined.isEmpty()) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("You have no jobs found!");

            return ResponseEntity.ok(responseModel);
        }

        return ResponseEntity.ok(combined);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable("id") Integer id) {

        if (!jobService.exists(id)) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("You have no jobs found!");

            return ResponseEntity.ok(responseModel);
        }

        Optional<JobEntity> job = jobService.findById(id);
        return ResponseEntity.ok(job);
    }

    @RequestMapping(value = "/getjobsbygoogleaccount", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam String email) {
        List<JobEntity> jobs = jobService.findAllPostedJobs(email);
        HashMap<String, Object> combined = new HashMap<>();
        combined.put(KEY_JOBS, jobs);
        combined.put(KEY_CATEGORIES, jobs);

        if (combined.isEmpty()) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("You have no jobs found!");

            return ResponseEntity.ok(responseModel);
        }

        return ResponseEntity.ok(combined);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody JobEntity job) {
        jobService.save(job);
        return ResponseEntity.ok(job);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody JobEntity jobEntity) {

        if (jobService.exists(id)) {
            jobService.update(jobEntity);
            return ResponseEntity.ok(jobEntity);
        } else {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("You have no jobs found!");
            return ResponseEntity.ok(responseModel);
        }
    }

    @RequestMapping(value = "registerjob/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> registerjob(@PathVariable("id") Integer id, @RequestBody JobEntity jobEntity) {

        if (jobService.exists(id)) {
            jobService.update(jobEntity);

            return ResponseEntity.ok(jobEntity);
        } else {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("You have no jobs found!");

            return ResponseEntity.ok(responseModel);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {

        if (!jobService.exists(id)) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("You have no jobs found!");

            return ResponseEntity.ok(responseModel);
        }else {
            jobService.delete(id);

            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("You deleted job successfully!");

            return ResponseEntity.ok(responseModel);
        }
    }

    @RequestMapping(value = "/getappliedjobsbygoogleaccount", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAppliedJobsByGooogleAccount(@RequestParam String googleAccountEmail) {
        List<JobEntity> jobs = jobService.findApplyedJobs(googleAccountEmail);
        HashMap<String, Object> combined = new HashMap<>();
        combined.put(KEY_JOBS, jobs);
        combined.put(KEY_CATEGORIES, jobs);

        if (combined.isEmpty()) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("You have no jobs found!");
        }

        return ResponseEntity.ok(combined);
    }

    @RequestMapping(value = "deleteall", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteaLL() {
        jobService.deleteAll();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}