package com.futumap.webapi.service;

import com.futumap.webapi.dao.entity.JobCategoryEntity;
import com.futumap.webapi.dao.entity.JobEntity;
import java.util.List;
import java.util.Optional;

public interface IJobService {

    List<JobEntity> findAll();
    List<JobEntity> findNearestJobs(Double longitude, Double latitude, Double distance, String accountGoogleId);
    List<JobEntity> findAllCurrentUserJobs(String accountGoogleId);
    JobEntity save (JobEntity jobEntity);
    JobEntity update(JobEntity jobEntity);
    void delete(Integer id);
    Optional<JobEntity> findById(Integer id);
    boolean exists(Integer id);
}
