package com.futumap.webapi.service;

import com.futumap.webapi.dao.entity.JobApplicationEntity;
import com.futumap.webapi.dao.entity.JobEntity;
import com.futumap.webapi.dao.entity.UserEntity;
import com.futumap.webapi.dto.JobApplicationDTO;
import com.futumap.webapi.respository.JobApplicationRepository;
import com.futumap.webapi.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobApplicationService implements IJobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<JobApplicationEntity> findAll() {
        return jobApplicationRepository.findAll();
    }

    @Override
    public List<JobApplicationDTO> findNonApprovedJobApplications(int userId) {
        return jobApplicationRepository.getNonApprovedJobApplications(userId);
    }

    @Override
    public void apply(int userId) {
        jobApplicationRepository.apply(userId);
    }


    @Override
    public List<JobApplicationEntity> findApprovedJobApplications(int userId) {
        return jobApplicationRepository.getApprovedJobApplications(userId);
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