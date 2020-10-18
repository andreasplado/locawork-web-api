package com.futumap.webapi.service;

import com.futumap.webapi.dao.entity.JobEntity;
import com.futumap.webapi.dao.entity.SettingsEntity;

import java.util.List;

public interface ISettingsService {

    List<SettingsEntity> findAll();
    List<SettingsEntity> getUserSettings(Integer userId);
    void updateRadius(Integer userId, Double radius);
    void updateViewByDefault(Integer userId, String value);
    void updateAskPermissionBeforeDeletingAJob(Integer userId, Boolean value);
    void updateShowInformationOnStartup(Integer userId, Boolean value);

}
