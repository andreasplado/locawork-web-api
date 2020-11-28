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
    @Query(value="SELECT CASE WHEN COUNT(u)> 0 then true else false end FROM users u WHERE u.account_email=?1", nativeQuery = true)
    boolean existsByEmail(@Param("account_email") String accountEmail);

    @Query(value="SELECT u.* FROM users u WHERE u.account_email=?1", nativeQuery = true)
    UserEntity findByEmail(@Param("account_email") String accountEmail);

    @Query(value="SELECT u.* FROM users u WHERE u.id=?1", nativeQuery = true)
    UserEntity findSingleById(@Param("id") Integer id);

    @Query(value="SELECT CASE WHEN COUNT(u)> 0 then true else false end FROM users u WHERE u.account_email=?1 AND u.contact=?2", nativeQuery = true)
    boolean emailAndPhoneNumberMatches(@Param("account_email") String accountEmail, @Param("contact") String contact);
}
