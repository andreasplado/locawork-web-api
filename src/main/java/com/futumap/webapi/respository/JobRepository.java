package com.futumap.webapi.respository;

import com.futumap.webapi.dao.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Integer> {


    @Query(value="SELECT * \n" +
            "FROM jobs \n" +
            "WHERE ST_Dwithin(geom, st_point(?1, ?2,?3);", nativeQuery = true)
    List<JobEntity> findNearestJobs(@RequestParam("longitude") String longitude, @RequestParam("latitude") String latitude, @RequestParam("distance") String distance);
}