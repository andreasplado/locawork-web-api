package com.futumap.webapi.respository;

import com.futumap.webapi.dao.entity.JobCategoryEntity;
import com.futumap.webapi.dao.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Integer> {

    @Query(value="SELECT * from jobs j WHERE earth_box(ll_to_earth(?1,?2),?3) @> ll_to_earth(j.latitude,j.longitude) AND j.account_google_id !=?4 AND regitred_account IS NULL", nativeQuery = true)
    List<JobEntity> findNearestJobs(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("distance") Double distance, @Param("googleAccount") String accountGoogleId);

    @Query(value="SELECT * from jobs j WHERE account_google_id=?1", nativeQuery = true)
    List<JobEntity> findPostedJobs(@Param("googleAccount") String accountGoogleId);

    @Query(value="SELECT * from jobs j WHERE regitred_account=?1", nativeQuery = true)
    List<JobEntity> findAppliedJobs(@Param("googleAccount") String accountGoogleId);

    @Query(value="SELECT * from jobs j WHERE earth_box(ll_to_earth(?1,?2),?3) @> ll_to_earth(j.latitude,j.longitude)", nativeQuery = true)
    List<JobEntity> findAllNearestJobs(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("distance") Double distance);
}