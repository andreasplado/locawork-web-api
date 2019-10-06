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

    @Query(value="SELECT * FROM jobs WHERE ST_DWithin(location, ST_SetSRID(ST_MakePoint(?1, ?2), 3785), ?3);", nativeQuery = true)
    List<JobEntity> findNearestJobs(@Param("longitude") String longitude, @Param("latitude") String latitude, @Param("distance") String distance);
}