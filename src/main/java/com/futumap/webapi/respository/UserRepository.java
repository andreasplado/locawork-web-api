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
    @Query(value="SELECT * from users u WHERE u.account_google_id=?1", nativeQuery = true)
    UserEntity findByGoogleAccount(@Param("account_google_id") String googleAccountId);
}
