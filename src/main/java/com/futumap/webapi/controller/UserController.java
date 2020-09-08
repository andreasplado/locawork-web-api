package com.futumap.webapi.controller;

import com.futumap.webapi.dao.entity.UserEntity;
import com.futumap.webapi.model.ResponseModel;
import com.futumap.webapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<UserEntity> userEntities = userService.findAll();

        if (userEntities != null && userEntities.isEmpty()) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("Users not found!");
            return ResponseEntity.ok(responseModel);
        }

        return ResponseEntity.ok(userEntities);
    }

    @RequestMapping(value = "/check-user", method = RequestMethod.GET)
    public ResponseEntity<?> getByGoogleAccount(@RequestParam ("account_google_id") String accountGoogleId) {
        UserEntity userEntity = userService.findByGoogleAccount(accountGoogleId);

        if (userEntity == null) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("Users not found!");
            return ResponseEntity.ok(responseModel);
        }

        return ResponseEntity.ok(userEntity);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody UserEntity userEntity) {
        if(!userService.exists(userEntity.getAccountGoogleId())){
            userService.save(userEntity);
        }

        return ResponseEntity.ok(userEntity);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UserEntity userEntity) {

        if (!userService.exists(id)) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("User already exists!");
            return ResponseEntity.ok(responseModel);
        }
        userService.update(userEntity);

        return ResponseEntity.ok(userEntity);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Optional<UserEntity> userEntity = userService.findById(id);

        if (!userEntity.isPresent()) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("User not found!");
            return ResponseEntity.ok(responseModel);
        }

        userService.delete(id);
        return ResponseEntity.ok(userEntity);
    }
}
