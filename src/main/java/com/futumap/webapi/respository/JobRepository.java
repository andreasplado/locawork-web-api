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
            "INNER JOIN users u ON j.fk_user = u.id " +
            "WHERE earth_box(ll_to_earth(?1,?2),?3) @> ll_to_earth(j.latitude,j.longitude) AND u.email=?4", nativeQuery = true)
    List<JobEntity> findOtherUsersNearestJobs(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("distance") Double distance, @Param("email") String email);

    @Query(value="SELECT j.*, u.* from jobs j " +
            "INNER JOIN users u ON j.fk_user = u.id " +
            "WHERE u.email=?1", nativeQuery = true)
    List<JobEntity> findPostedJobs(@Param("email") String email);

    @Query(value="SELECT j.*, u.* from jobs j" +
            "INNER JOIN users u ON j.fk_user = u.id " +
            "WHERE earth_box(ll_to_earth(?1,?2),?3) @> ll_to_earth(j.latitude,j.longitude)", nativeQuery = true)
    List<JobEntity> findAllNearestJobs(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("distance") Double distance);
}