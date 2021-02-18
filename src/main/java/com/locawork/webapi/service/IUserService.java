package com.locawork.webapi.service;

import com.locawork.webapi.dao.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<UserEntity> findAll();
    UserEntity save(UserEntity userEntity);
    UserEntity update(UserEntity userEntity);
    void delete(Integer id);
    Optional<UserEntity> findById(Integer id);
    boolean exists(Integer id);
    boolean phoneNumberAndEmailMatches(String accountEmail, String phoneNumber);
    boolean existByEmail(String googleAccountId);
    UserEntity saveUser(UserEntity userEntity);
    UserEntity findByEmail(String email);
    int findId(String email);
}