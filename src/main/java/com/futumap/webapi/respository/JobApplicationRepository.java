package com.futumap.webapi.respository;

import com.futumap.webapi.dao.entity.JobApplicationEntity;
import com.futumap.webapi.dto.JobApplicationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplicationEntity, Integer> {

    @Query(value="SELECT ja.id, j.title, j.description, j.salary, ja.created_at, ja.updated_at, ja.job_id, ja.user_id, u.account_email FROM job_applications ja" +
            " INNER JOIN jobs j ON ja.job_id = j.id" +
            " INNER JOIN users u ON ja.user_id = u.id" +
            " WHERE j.user_id=?1 AND ja.is_approved=FALSE", nativeQuery = true)
    List<JobApplicationDTO> getNonApprovedJobApplications(@Param("user_id") int userId);


    @Modifying
    @Transactional
    @Query(value="DELETE FROM job_applications WHERE ja.user_id=?1", nativeQuery = true)
    void deleteJobApplication(@Param("user_id") int userId);

    @Modifying
    @Transactional
    @Query(value="UPDATE jobs j SET j.applyer_id=?1 WHERE j.user_id=?2", nativeQuery = true)
    void updateJob(@Param("applyer_id") int applyerId, @Param("user_id") int userId);

    @Query(value="SELECT ja.*, j.* FROM job_applications ja" +
            " INNER JOIN jobs j ON ja.fk_job = j.id" +
            " WHERE job_applications_pkey!=?1 AND ja.is_approved=TRUE", nativeQuery = true)
    List<JobApplicationEntity> getApprovedJobApplications(@Param("user_id") int userId);
}