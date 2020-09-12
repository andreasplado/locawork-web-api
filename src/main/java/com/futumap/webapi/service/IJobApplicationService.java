package com.futumap.webapi.service;

import com.futumap.webapi.dao.entity.JobApplicationEntity;
import com.futumap.webapi.dto.JobApplicationDTO;

import java.util.List;
import java.util.Optional;

public interface IJobApplicationService {

    List<JobApplicationEntity> findAll();
    List<JobApplicationDTO> findNonApprovedJobApplications(int userId);
    void apply(int userId);
    void update(int applyerId, int userId);
    List<JobApplicationEntity> findApprovedJobApplications(int userId);
    JobApplicationEntity save(JobApplicationEntity jobEntity);
    JobApplicationEntity update(JobApplicationEntity jobEntity);
    void delete(Integer id);
    void deleteAll();
    Optional<JobApplicationEntity> findById(Integer id);
    boolean exists(Integer id);
}
