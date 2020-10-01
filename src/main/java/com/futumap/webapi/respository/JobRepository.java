package com.futumap.webapi.respository;

import com.futumap.webapi.dao.entity.JobApplicationEntity;
import com.futumap.webapi.dao.entity.JobEntity;
import com.futumap.webapi.dto.MainDataDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Integer> {

    @Query(value="SELECT j.*, u.* from jobs j " +
            "INNER JOIN users u ON j.user_id = u.id " +
            "WHERE earth_box(ll_to_earth(?1,?2),?3) @> ll_to_earth(j.latitude,j.longitude) AND j.applyer_id IS NULL AND u.id!=?4 AND j.is_done=false", nativeQuery = true)
    List<JobEntity> findOtherUsersNearestJobs(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("distance") Double distance, @Param("userId") Integer userId);

    @Query(value="SELECT j.*, u.* from jobs j " +
            "INNER JOIN users u ON j.user_id = u.id " +
            "WHERE u.id=?1 AND j.is_done=false", nativeQuery = true)
    List<JobEntity> findPostedJobs(@Param("userId") Integer userId);

    @Query(value="SELECT j.*, u.* from jobs j " +
            "INNER JOIN users u ON j.user_id = u.id " +
            "WHERE earth_box(ll_to_earth(?1,?2),?3) @> ll_to_earth(j.latitude,j.longitude) AND j.applyer_id IS NULL AND j.is_done=false", nativeQuery = true)
    List<JobEntity> findAllNearestJobs(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("distance") Double distance);

    @Query(value="SELECT j.* from jobs j " +
            "WHERE j.applyer_id=?1 AND j.is_done=false", nativeQuery = true)
    List<JobEntity> findByUserId(@Param("userId") Integer userId);

    @Modifying
    @Transactional
    @Query(value="UPDATE jobs SET is_done=true WHERE applyer_id=?1", nativeQuery = true)
    void applyToJob(@Param("applyer_id") int applyerId);
}