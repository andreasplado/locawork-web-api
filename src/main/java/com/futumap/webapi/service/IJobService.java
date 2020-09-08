package com.futumap.webapi.service;

import com.futumap.webapi.dao.entity.JobApplicationEntity;
import com.futumap.webapi.dao.entity.JobEntity;
import java.util.List;
import java.util.Optional;

public interface IJobService {

    List<JobEntity> findAll();
    List<JobEntity> findOtherUsersNearestJobs(Double longitude, Double latitude, Double distance, String email);

    List<JobEntity> findAllPostedJobs(String accountGoogleId);
    List<JobEntity> findAllNearestJobs(Double latitude, Double longitude, Double distance);
    JobEntity save (JobEntity jobEntity);
    JobEntity update(JobEntity jobEntity);
    void delete(Integer id);
    void deleteAll();
    Optional<JobEntity> findById(Integer id);
    boolean exists(Integer id);
}
