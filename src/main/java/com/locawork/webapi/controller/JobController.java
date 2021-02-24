package com.locawork.webapi.controller;

import com.locawork.webapi.dao.entity.JobCategoryEntity;
import com.locawork.webapi.dao.entity.JobEntity;
import com.locawork.webapi.dao.entity.UserEntity;
import com.locawork.webapi.dto.JobApplicationDTO;
import com.locawork.webapi.model.MainData;
import com.locawork.webapi.model.ResponseModel;
import com.locawork.webapi.service.JobApplicationService;
import com.locawork.webapi.service.JobCategoryService;
import com.locawork.webapi.service.JobService;
import com.locawork.webapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private static String KEY_JOBS = "jobs";
    private static String KEY_CATEGORIES = "categories";
    private static String KEY_USER = "user";

    @Autowired
    private UserService userservice;

    @Autowired
    private JobService workService;

    @Autowired
    private JobApplicationService jobApplicationService;

    @Autowired
    private JobCategoryService jobCategoryService;

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> getAll() {
        List<JobEntity> jobs = workService.findAll();
        List<JobCategoryEntity> categories = jobCategoryService.findAll();
        HashMap<String, Object> combined = new HashMap<>();

        combined.put(KEY_JOBS, jobs);
        combined.put(KEY_CATEGORIES, categories);

        if (combined.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(combined, HttpStatus.OK);
    }


    @RequestMapping(value = "/start-work", method = RequestMethod.POST)
    public ResponseEntity<ResponseModel> apply(@RequestParam Integer applyerId) {
        workService.applyToJob(applyerId);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setMessage("You started work!");

        return ResponseEntity.ok(responseModel);
    }

    @RequestMapping(value = "/get-available-jobs", method = RequestMethod.GET)
    public ResponseEntity<?> getUserOffers(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam Double distance, @RequestParam Integer userId) {
        List<JobEntity> jobs = workService.findOtherUsersNearestJobs(latitude, longitude, distance, userId);
        List<JobCategoryEntity> categories = jobCategoryService.findAll();

        UserEntity user = userservice.findUserById(userId);
        HashMap<String, Object> combined = new HashMap<>();
        combined.put(KEY_JOBS, jobs);
        combined.put(KEY_CATEGORIES, categories);
        combined.put(KEY_USER, user);

        return ResponseEntity.ok(combined);
    }

    @RequestMapping(value = "/getalljobsbylocation", method = RequestMethod.GET)
    public ResponseEntity<?> getAllJobsByLocation(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam Double distance) {
        List<JobEntity> jobs = workService.findAllNearestJobs(latitude, longitude, distance);
        List<JobCategoryEntity> categories = jobCategoryService.findAll();
        HashMap<String, Object> combined = new HashMap<>();
        combined.put(KEY_JOBS, jobs);
        combined.put(KEY_CATEGORIES, categories);

        return ResponseEntity.ok(combined);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable("id") Integer id) {

        Optional<JobEntity> job = workService.findById(id);
        return ResponseEntity.ok(job);
    }

    @RequestMapping(value = "/getjobsbygoogleaccount", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam Integer userId) {
        List<JobEntity> jobs = workService.findAllPostedJobs(userId);
        List<JobCategoryEntity> categories = jobCategoryService.findAll();
        UserEntity user = userservice.findUserById(userId);
        HashMap<String, Object> combined = new HashMap<>();
        combined.put(KEY_JOBS, jobs);
        combined.put(KEY_CATEGORIES, categories);
        combined.put(KEY_USER, user);

        return ResponseEntity.ok(combined);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody JobEntity job) {
        workService.save(job);
        return ResponseEntity.ok(job);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody JobEntity jobEntity) {

        if (workService.exists(id)) {
            workService.update(jobEntity);
            return ResponseEntity.ok(jobEntity);
        } else {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("You have no jobs found!");
            return ResponseEntity.ok(responseModel);
        }
    }

    @RequestMapping(value = "registerjob/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> registerjob(@PathVariable("id") Integer id, @RequestBody JobEntity jobEntity) {

        if (workService.exists(id)) {
            workService.update(jobEntity);

            return ResponseEntity.ok(jobEntity);
        } else {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("You have no jobs found!");

            return ResponseEntity.ok(responseModel);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {

        if (!workService.exists(id)) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("You have no jobs found!");

            return ResponseEntity.ok(responseModel);
        }else {
            JobEntity jobEntity = workService.findSingleById(id);
            workService.delete(id);


            return ResponseEntity.ok(jobEntity);
        }
    }

    @RequestMapping(value = "/get-my-upcoming-work", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAppliedJobsByGooogleAccount(@RequestParam Integer userId) {
        List<JobEntity> jobs = workService.findUpcomingWork(userId);
        HashMap<String, Object> combined = new HashMap<>();
        combined.put(KEY_JOBS, jobs);
        combined.put(KEY_CATEGORIES, jobs);

        if (combined.isEmpty()) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("You have no jobs found!");
        }

        return ResponseEntity.ok(combined);
    }

    @RequestMapping(value = "/getmydonework", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getMyDoneWork(@RequestParam Integer userId) {
        List<JobEntity> jobs = workService.findMyDoneWork(userId);
        HashMap<String, Object> combined = new HashMap<>();
        combined.put(KEY_JOBS, jobs);
        combined.put(KEY_CATEGORIES, jobs);

        if (combined.isEmpty()) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("You have no jobs found!");
        }

        return ResponseEntity.ok(combined);
    }

    @RequestMapping(value = "/get-main-data", method = RequestMethod.GET)
    public ResponseEntity<MainData> getMainData(@RequestParam Integer userId) {
        List<JobEntity> applyedJobs = workService.findUpcomingWork(userId);
        List<JobApplicationDTO> myCandidates = jobApplicationService.findCandidates(userId);

        MainData mainData = new MainData();
        mainData.setMyUpcomingWorkNumber(applyedJobs.size());
        mainData.setMyCandidatesNumber(myCandidates.size());

        return ResponseEntity.ok(mainData);
    }

    @RequestMapping(value = "deleteall", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteaLL() {
        workService.deleteAll();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}