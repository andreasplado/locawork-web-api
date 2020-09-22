package com.futumap.webapi.service;

import com.futumap.webapi.dao.entity.JobApplicationEntity;
import com.futumap.webapi.dto.JobApplicationDTO;
import com.futumap.webapi.dto.MyApplicationDTO;

import java.util.List;
import java.util.Optional;

public interface IJobApplicationService {

    List<JobApplicationEntity> findAll();
    List<JobApplicationDTO> findCandidates(int userId);
    List<MyApplicationDTO> findMyApplications(int userId);
    List<JobApplicationEntity> existsJobByUserId(Integer userId, Integer jobId);
    void deleteJobApplications(int userId);
    void update(int applyerId, int userId);
    List<JobApplicationEntity> findApprovedJobApplications(int userId);
    JobApplicationEntity save(JobApplicationEntity jobEntity);
    JobApplicationEntity update(JobApplicationEntity jobEntity);
    JobApplicationEntity delete(Integer id);
    void deleteAll();
    Optional<JobApplicationEntity> findById(Integer id);
    boolean exists(Integer id);

    void deleteAllByJobId(Integer id);
}
