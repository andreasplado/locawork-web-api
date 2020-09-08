package com.futumap.webapi.controller;

import com.futumap.webapi.dao.entity.JobApplicationEntity;
import com.futumap.webapi.dao.entity.JobCategoryEntity;
import com.futumap.webapi.dao.entity.JobEntity;
import com.futumap.webapi.model.ResponseModel;
import com.futumap.webapi.service.JobApplicationService;
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
@RequestMapping("/jobapplications")
public class JobApplicationController {

    private static String KEY_JOBS = "job_applications";

    @Autowired
    private JobApplicationService jobApplicationService;

    @RequestMapping(method = RequestMethod.POST)
    public JobApplicationEntity create(@RequestBody JobApplicationEntity jobApplicationEntity) {
        jobApplicationService.save(jobApplicationEntity);
        return jobApplicationEntity;
    }

    @RequestMapping(value = "/getalljobapplications", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAppliedJobsByGooogleAccount(@RequestParam String googleAccountEmail) {
        List<JobApplicationEntity> jobApplications = jobApplicationService.findJobApplications(googleAccountEmail);
        HashMap<String, Object> combined = new HashMap<>();
        combined.put(KEY_JOBS, jobApplications);

        if (combined.isEmpty()) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("You have no jobs found!");
        }

        return ResponseEntity.ok(combined);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {

        if (!jobApplicationService.exists(id)) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("You have no jobs found!");

            return ResponseEntity.ok(responseModel);
        }else {
            jobApplicationService.delete(id);

            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("You deleted job successfully!");

            return ResponseEntity.ok(responseModel);
        }


    }
}