package com.futumap.webapi.service;

import com.futumap.webapi.dao.entity.JobApplicationEntity;
import com.futumap.webapi.dao.entity.JobEntity;
import com.futumap.webapi.dao.entity.UserEntity;
import com.futumap.webapi.dto.JobApplicationDTO;
import com.futumap.webapi.respository.JobApplicationRepository;
import com.futumap.webapi.respository.JobRepository;
import com.futumap.webapi.respository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService implements IJobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<JobApplicationEntity> findAll() {
        return jobApplicationRepository.findAll();
    }

    @Override
    public List<JobApplicationDTO> findNonApprovedJobApplications(int userId) {
        List<JobApplicationEntity> jobApplicationEntities = jobApplicationRepository.getNonApprovedJobApplications(userId);


        List<JobApplicationDTO> jobApplicationDTOS = new ArrayList<>();
        for(int i=0; i<jobApplicationEntities.size(); i++){

            JobEntity jobEntity = jobRepository.findSingleById(jobApplicationEntities.get(i).getJob());
            UserEntity userEntity = userRepository.findSingleById(jobApplicationEntities.get(i).getUserId());
            JobApplicationDTO jobApplicationDTO = new JobApplicationDTO();
            jobApplicationDTO.setJobTitle(jobEntity.getTitle());
            jobApplicationDTO.setJobSalary(jobEntity.getSalary());
            jobApplicationDTO.setSetEmail(userEntity.getEmail());
            jobApplicationDTOS.add(jobApplicationDTO);
        }

        return jobApplicationDTOS;
    }

    private JobApplicationDTO convert(JobApplicationDTO myObject) {
        // conversion here
        return new JobApplicationDTO();
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