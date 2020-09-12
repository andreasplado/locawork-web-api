package com.futumap.webapi.respository;

import com.futumap.webapi.dao.entity.JobApplicationEntity;
import com.futumap.webapi.dao.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Integer> {

    @Query(value="SELECT j.*, u.* from jobs j " +
            "INNER JOIN users u ON j.user_id = u.id " +
            "WHERE earth_box(ll_to_earth(?1,?2),?3) @> ll_to_earth(j.latitude,j.longitude) AND u.id!=?4", nativeQuery = true)
    List<JobEntity> findOtherUsersNearestJobs(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("distance") Double distance, @Param("userId") Integer userId);

    @Query(value="SELECT j.*, u.* from jobs j " +
            "INNER JOIN users u ON j.user_id = u.id " +
            "WHERE u.id=?1", nativeQuery = true)
    List<JobEntity> findPostedJobs(@Param("userId") Integer userId);

    @Query(value="SELECT j.*, u.* from jobs j " +
            "INNER JOIN users u ON j.user_id = u.id " +
            "WHERE earth_box(ll_to_earth(?1,?2),?3) @> ll_to_earth(j.latitude,j.longitude) AND j.applyer_id IS NULL", nativeQuery = true)
    List<JobEntity> findAllNearestJobs(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("distance") Double distance);

    @Query(value="SELECT j.*, u.* from jobs j " +
            "INNER JOIN users u ON j.applyer_id = u.id " +
            "WHERE j.applyer_id=0", nativeQuery = true)
    List<JobEntity> findApplyedJobs();

    @Query(value="SELECT j.* from jobs j " +
            "WHERE j.account_email=?1", nativeQuery = true)
    JobEntity findByEmail(String email);

    @Query(value="SELECT j.* from jobs j " +
            "WHERE j.id=?1", nativeQuery = true)
    JobEntity findSingleById(Integer id);
}