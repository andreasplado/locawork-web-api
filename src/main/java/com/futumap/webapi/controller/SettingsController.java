package com.futumap.webapi.controller;


import com.futumap.webapi.dao.entity.JobCategoryEntity;
import com.futumap.webapi.dao.entity.SettingsEntity;
import com.futumap.webapi.model.ResponseModel;
import com.futumap.webapi.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    SettingsService settingsService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<SettingsEntity> settings = settingsService.findAll();

        return ResponseEntity.ok(settings);
    }

    @RequestMapping(value = "/get-user-settings", method = RequestMethod.GET)
    public ResponseEntity<?> getSettings(@RequestParam Integer userId) {
        SettingsEntity settings = settingsService.getUserSettings(userId);

        return ResponseEntity.ok(settings);
    }

    @RequestMapping(value = "/set-initial-settings", method = RequestMethod.POST)
    public ResponseEntity<?> setInitialSettings(@RequestBody SettingsEntity settingsEntity) {
        ResponseModel responseModel = new ResponseModel();

        if(!settingsService.exists(settingsEntity.getUserId())) {
            settingsService.save(settingsEntity);

            return ResponseEntity.ok(settingsEntity);
        }else{
            responseModel.setMessage("These settings for user with id: " + settingsEntity.getUserId() + "already exist!");

            return ResponseEntity.ok(responseModel);
        }


    }


    @RequestMapping(value = "/update-radius", method = RequestMethod.PUT)
    public ResponseEntity<?> updateRadius(@RequestParam Integer userId, @RequestParam Double radius) {
        ResponseModel responseModel = new ResponseModel();

        settingsService.updateRadius(userId, radius);
        responseModel.setMessage("You updated radius!");

        return ResponseEntity.ok(responseModel);
    }

    @RequestMapping(value = "/update-view-by-default", method = RequestMethod.PUT)
    public ResponseEntity<?> updateViewByDefault(@RequestParam Integer userId, @RequestParam String value) {
        ResponseModel responseModel = new ResponseModel();

        settingsService.updateViewByDefault(userId, value);
        responseModel.setMessage("You updated view by default!");

        return ResponseEntity.ok(responseModel);
    }

    @RequestMapping(value = "/update-ask-permissions-before-deleting-a-job", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAskPermissionsBeforeDeletingAJob(@RequestParam Integer userId, @RequestParam Boolean value) {
        ResponseModel responseModel = new ResponseModel();

        settingsService.updateAskPermissionBeforeDeletingAJob(userId, value);
        responseModel.setMessage("You updated view by default!");

        return ResponseEntity.ok(responseModel);
    }

    @RequestMapping(value = "/update-show-information-on-startup", method = RequestMethod.PUT)
    public ResponseEntity<?> updateShowInformationOnStartup(@RequestParam Integer userId, @RequestParam Boolean value) {
        ResponseModel responseModel = new ResponseModel();

        settingsService.updateShowInformationOnStartup(userId, value);
        responseModel.setMessage("You updated view by default!");

        return ResponseEntity.ok(responseModel);
    }
}
