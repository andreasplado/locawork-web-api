package com.futumap.webapi.respository;

import com.futumap.webapi.dao.entity.JobCategoryEntity;
import com.futumap.webapi.dao.entity.JobEntity;
import com.futumap.webapi.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query(value="SELECT * from users j WHERE j.username ==?1", nativeQuery = true)
    UserEntity findByUsername(@Param("username") String username);

    @Query(value="SELECT count (*) from users j WHERE j.username >?1", nativeQuery = true)
    BigInteger userExists(@Param("username") String username);

}
