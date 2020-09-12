package com.futumap.webapi.respository;

import com.futumap.webapi.dao.entity.JobApplicationEntity;
import com.futumap.webapi.dao.entity.JobEntity;
import com.futumap.webapi.dto.JobApplicationDTO;
import com.futumap.webapi.service.IJobApplicationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplicationEntity, Integer> , IJobApplicationDTO {

    @Query(value="SELECT ja.id, ja.is_approved, j.title, j.description, j.salary, ja.created_at, ja.updated_at, ja.job_id, ja.user_id FROM job_applications ja" +
            " INNER JOIN jobs j ON ja.job_id = j.id" +
            " INNER JOIN users u ON ja.user_id = u.id" +
            " WHERE ja.job_id!=?1 AND ja.is_approved=FALSE", nativeQuery = true)
    List<JobApplicationDTO> getNonApprovedJobApplications(@Param("user_id") int userId);

    @Query(value="SELECT ja.*, j.* FROM job_applications ja" +
            " INNER JOIN jobs j ON ja.fk_job = j.id" +
            " WHERE job_applications_pkey!=?1 AND ja.is_approved=TRUE", nativeQuery = true)
    List<JobApplicationEntity> getApprovedJobApplications(@Param("user_id") int userId);
}