package com.locawork.webapi.service;

import com.locawork.webapi.dao.entity.SettingsEntity;
import com.locawork.webapi.respository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingsService implements ISettingsService {

    @Autowired
    SettingsRepository settingsRepository;

    @Override
    public SettingsEntity save(SettingsEntity settingsEntity) {
        return settingsRepository.save(settingsEntity);
    }

    @Override
    public boolean exists(Integer userId) {
        return settingsRepository.exists(userId);
    }

    @Override
    public List<SettingsEntity> findAll() {
        return settingsRepository.findAll();
    }

    @Override
    public SettingsEntity getUserSettings(Integer userId) {
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
        settingsRepository.setAskPermissionsBeforeDeletingAJob(value, userId);
    }

    @Override
    public void updateShowInformationOnStartup(Integer userId, Boolean value) {
        settingsRepository.setShowInformationOnStartup(value, userId);
    }

    @Override
    public void updateBiometric(Integer userId, Boolean value) {
        settingsRepository.updateBiometric(value, userId);
    }

}
