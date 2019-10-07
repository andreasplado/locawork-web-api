package com.futumap.webapi.service;

import com.futumap.webapi.dao.entity.JobEntity;
import com.futumap.webapi.respository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class JobService implements IJobService {

    @Autowired
    private JobRepository repository;

    @Override
    public List<JobEntity> findAll() {
        return repository.findAll();
    }


    @Override
    public List<JobEntity> findNearestJobs(Double longitude, Double latitude, Double distance) {
        BigDecimal convertedLong = new BigDecimal(longitude);
        BigDecimal convertedLat = new BigDecimal(latitude);
        return repository.findNearestJobs(convertedLong, convertedLat, distance);
    }

    @Override
    public JobEntity save(JobEntity jobEntity) {
        return repository.save(jobEntity);
    }


    @Override
    public JobEntity update(JobEntity jobEntity) {
        if(repository.existsById(jobEntity.getId())){
            repository.save(jobEntity);
        }
        return jobEntity;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<JobEntity> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return repository.existsById(id);
    }

}