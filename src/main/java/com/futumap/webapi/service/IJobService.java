package com.futumap.webapi.service;

import com.futumap.webapi.dao.entity.JobEntity;

import java.util.List;
import java.util.Optional;

public interface IJobService {

    List<JobEntity> findAll();
    List<JobEntity> findNearestJobs(String longitude, String latitude, String radius);
    JobEntity save (JobEntity jobEntity);
    JobEntity update(JobEntity jobEntity);
    void delete(Integer id);
    Optional<JobEntity> findById(Integer id);
    boolean exists(Integer id);
}
