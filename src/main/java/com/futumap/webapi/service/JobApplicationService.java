package com.futumap.webapi.service;

import com.futumap.webapi.dao.entity.JobApplicationEntity;
import com.futumap.webapi.respository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService implements IJobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Override
    public List<JobApplicationEntity> findAll() {
        return jobApplicationRepository.findAll();
    }

    @Override
    public List<JobApplicationEntity> findNonApprovedJobApplications(String accountGoogleId) {
        return jobApplicationRepository.getNonApprovedJobApplications(accountGoogleId);
    }

    @Override
    public List<JobApplicationEntity> findApprovedJobApplications(String accountGoogleId) {
        return jobApplicationRepository.getApprovedJobApplications(accountGoogleId);
    }


    @Override
    public JobApplicationEntity save(JobApplicationEntity jobEntity) {
        return jobApplicationRepository.save(jobEntity);
    }

    @Override
    public JobApplicationEntity update(JobApplicationEntity jobApplicationEntity) {
        if(jobApplicationRepository.existsById(jobApplicationEntity.getId())){
            jobApplicationRepository.save(jobApplicationEntity);
        }
        return jobApplicationEntity;
    }


    @Override
    public void delete(Integer id) {
        jobApplicationRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        jobApplicationRepository.deleteAll();
    }

    @Override
    public Optional<JobApplicationEntity> findById(Integer id) {
        return jobApplicationRepository.findById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return jobApplicationRepository.existsById(id);
    }

}