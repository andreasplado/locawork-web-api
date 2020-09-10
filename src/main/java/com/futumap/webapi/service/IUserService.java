package com.futumap.webapi.service;

import com.futumap.webapi.dao.entity.JobEntity;
import com.futumap.webapi.dao.entity.UserEntity;
import org.apache.catalina.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<UserEntity> findAll();
    UserEntity save(UserEntity userEntity);
    UserEntity update(UserEntity userEntity);
    void delete(Integer id);
    Optional<UserEntity> findById(Integer id);
    boolean exists(Integer id);
    boolean existByEmail(String googleAccountId);
    UserEntity saveUser(UserEntity userEntity);
}
