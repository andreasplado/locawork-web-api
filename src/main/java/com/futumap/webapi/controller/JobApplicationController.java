package com.futumap.webapi.controller;

import com.futumap.webapi.dao.entity.JobApplicationEntity;
import com.futumap.webapi.dto.JobApplicationDTO;
import com.futumap.webapi.model.ResponseModel;
import com.futumap.webapi.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/jobapplications")
public class JobApplicationController {

    private static String KEY_JOB_APPLICATIONS = "job_applications";

    @Autowired
    private JobApplicationService jobApplicationService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody JobApplicationEntity jobApplicationEntity) {
        jobApplicationService.save(jobApplicationEntity);
        return ResponseEntity.ok(jobApplicationEntity);
    }

    @RequestMapping(value = "/get-non-approved-job-applications", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getNonApprovedJobApplications(@RequestParam int userId) {
        List<JobApplicationDTO> jobApplications = jobApplicationService.findNonApprovedJobApplications(userId);
        HashMap<String, Object> combined = new HashMap<>();
        combined.put(KEY_JOB_APPLICATIONS, jobApplications);

        if (combined.isEmpty()) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("You have no jobs found!");
        }

        return ResponseEntity.ok(combined);
    }

    @RequestMapping(value = "/deleteJobApplications", method = RequestMethod.POST)
    public ResponseEntity<ResponseModel> apply(@RequestParam Integer userId, @RequestParam Integer appyerId) {
        jobApplicationService.deleteJobApplications(userId);
        jobApplicationService.update(appyerId, userId);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setMessage("You applied to job!");

        return ResponseEntity.ok(responseModel);
    }

    @RequestMapping(value = "/get-approved-job-applications", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getApprovedJobApplications(@RequestParam int userId) {
        List<JobApplicationEntity> jobApplications = jobApplicationService.findApprovedJobApplications(userId);
        HashMap<String, Object> combined = new HashMap<>();
        combined.put(KEY_JOB_APPLICATIONS, jobApplications);

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