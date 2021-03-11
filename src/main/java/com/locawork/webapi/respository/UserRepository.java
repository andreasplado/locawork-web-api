package com.locawork.webapi.respository;

import com.locawork.webapi.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query(value="SELECT CASE WHEN COUNT(u)> 0 then true else false end FROM users u WHERE u.account_email=?1", nativeQuery = true)
    boolean existsByEmail(@Param("account_email") String accountEmail);

    @Query(value="SELECT u.* FROM users u WHERE u.account_email=?1", nativeQuery = true)
    UserEntity findByEmail(@Param("account_email") String accountEmail);

    @Query(value="SELECT u.* FROM users u WHERE u.id=?1", nativeQuery = true)
    UserEntity findSingleById(@Param("id") Integer id);

    @Query(value="SELECT CASE WHEN COUNT(u)> 0 then true else false end FROM users u WHERE u.account_email=?1 AND u.contact=?2", nativeQuery = true)
    boolean emailAndPhoneNumberMatches(@Param("account_email") String accountEmail, @Param("contact") String contact);

    @Query(value="SELECT u.id FROM users u WHERE u.account_email=?1", nativeQuery = true)
    int findId(@Param("username") String username);

    @Query(value="SELECT u.* FROM users u WHERE u.id=?1", nativeQuery = true)
    UserEntity findUserById(@Param("id") Integer username);

    @Query(value="SELECT u.firebase_token FROM users u WHERE u.id=?1", nativeQuery = true)
    String getUserFirebaseToken(@Param("id") Integer username);
}
