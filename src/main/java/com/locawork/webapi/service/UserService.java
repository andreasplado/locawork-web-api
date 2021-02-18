package com.locawork.webapi.service;

import com.locawork.webapi.dao.entity.UserEntity;
import com.locawork.webapi.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository repository;
    @Override
    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return repository.save(userEntity);
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        if(repository.existsById(userEntity.getId())){
            repository.save(userEntity);
        }
        return userEntity;
    }

    @Override
    public boolean existByEmail(String email){
        return repository.existsByEmail(email);
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        return null;
    }

    @Override
    public UserEntity findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public int findId(String email) {
        return repository.findId(email);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public boolean phoneNumberAndEmailMatches(String email, String phoneNumber) {
        if(repository.emailAndPhoneNumberMatches(email, phoneNumber)){
            return true;
        }
        return false;
    }
}
