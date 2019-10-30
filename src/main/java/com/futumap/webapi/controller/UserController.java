package com.futumap.webapi.controller;

import com.futumap.webapi.dao.entity.JobCategoryEntity;
import com.futumap.webapi.dao.entity.UserEntity;
import com.futumap.webapi.respository.UserRepository;
import com.futumap.webapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<UserEntity>> getAll() {
        List<UserEntity> userEntities = userService.findAll();

        if (userEntities != null && userEntities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(userEntities, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody UserEntity userEntity, UriComponentsBuilder ucBuilder) {
        userService.save(userEntity);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/city/{id}").buildAndExpand(userEntity.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserEntity> update(@PathVariable Integer id, @RequestBody UserEntity userEntity) {

        if (!userService.exists(id)) {
            return new ResponseEntity<UserEntity>(HttpStatus.NOT_FOUND);
        }
        userService.update(userEntity);
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        Optional<UserEntity> cityEntity = userService.findById(id);

        if (!cityEntity.isPresent()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        userService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
