package com.futumap.webapi.respository;

import com.futumap.webapi.dao.entity.JobApplicationEntity;
import com.futumap.webapi.dao.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplicationEntity, Integer> {

    @Query(value="SELECT ja, j FROM job_applications ja INNER JOIN ja.job j WHERE j.account_google_id !=?1", nativeQuery = true)
    List<JobApplicationEntity> getJobApplications(@Param("googleAccount") String accountGoogleId);
}