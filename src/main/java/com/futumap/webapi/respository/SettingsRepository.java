package com.futumap.webapi.respository;

import com.futumap.webapi.dao.entity.JobEntity;
import com.futumap.webapi.dao.entity.SettingsEntity;
import com.futumap.webapi.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SettingsRepository extends JpaRepository<SettingsEntity, Integer> {

    @Query(value="SELECT s.* from settings s " +
            "WHERE s.user_id=?1", nativeQuery = true)
    List<SettingsEntity> findUserSettings(@Param("user_id") Integer userId);

    @Modifying
    @Transactional
    @Query(value="UPDATE settings SET radius=?1 WHERE user_id=?2", nativeQuery = true)
    void setRadius(@Param("radius") Double radius, @Param("user_id") Integer userId);

    @Modifying
    @Transactional
    @Query(value="UPDATE settings SET view_by_default=?1 WHERE user_id=?2", nativeQuery = true)
    void setViewByDefault(@Param("value") String value, @Param("user_id") Integer userId);


    @Modifying
    @Transactional
    @Query(value="UPDATE settings SET ask_permissions_before_deleting_a_job=?1 WHERE user_id=?2", nativeQuery = true)
    void setAskPermissionsBeforeDeletingAJob(@Param("value") Boolean value, @Param("user_id") Integer userId);

    @Modifying
    @Transactional
    @Query(value="UPDATE settings SET show_information_on_startup=?1 WHERE user_id=?2", nativeQuery = true)
    void setShowInformationOnStartup(@Param("value") Boolean value, @Param("user_id") Integer userId);
}
