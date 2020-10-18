package com.futumap.webapi.service;

import com.futumap.webapi.dao.entity.SettingsEntity;
import com.futumap.webapi.respository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SettingsService implements ISettingsService {

    @Autowired
    SettingsRepository settingsRepository;

    @Override
    public List<SettingsEntity> findAll() {
        return settingsRepository.findAll();
    }

    @Override
    public List<SettingsEntity> getUserSettings(Integer userId) {
        return settingsRepository.findUserSettings(userId);
    }

    @Override
    public void updateRadius(Integer userId, Double radius) {
        settingsRepository.setRadius(radius, userId);
    }

    @Override
    public void updateViewByDefault(Integer userId, String value) {
        settingsRepository.setViewByDefault(value, userId);
    }

    @Override
    public void updateAskPermissionBeforeDeletingAJob(Integer userId, Boolean value) {

    }

    @Override
    public void updateShowInformationOnStartup(Integer userId, Boolean value) {

    }

}
