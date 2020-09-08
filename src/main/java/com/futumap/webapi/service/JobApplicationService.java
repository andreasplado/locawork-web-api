package com.futumap.webapi.service;

import com.futumap.webapi.dao.entity.JobApplicationEntity;
import com.futumap.webapi.dao.entity.JobEntity;
import com.futumap.webapi.respository.JobApplicationRepository;
import com.futumap.webapi.respository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService implements IJobApplicationService {

    @Autowired
    private JobApplicationRepository repository;

    @Override
    public List<JobApplicationEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public List<JobApplicationEntity> findJobApplications(String accountGoogleId) {
        return repository.getJobApplications(accountGoogleId);
    }


    @Override
    public JobApplicationEntity save(JobApplicationEntity jobEntity) {
        return repository.save(jobEntity);
    }

    @Override
    public JobApplicationEntity update(JobApplicationEntity jobApplicationEntity) {
        if(repository.existsById(jobApplicationEntity.getId())){
            repository.save(jobApplicationEntity);
        }
        return jobApplicationEntity;
    }


    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Optional<JobApplicationEntity> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return repository.existsById(id);
    }

}