package com.futumap.webapi.controller;

import com.futumap.webapi.config.JwtTokenUtil;
import com.futumap.webapi.dao.entity.JwtRequest;
import com.futumap.webapi.dao.entity.JwtResponse;
import com.futumap.webapi.dao.entity.UserEntity;
import com.futumap.webapi.model.ResponseModel;
import com.futumap.webapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userService
                .findByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody UserEntity userEntity)  {
        if(userService.existsByUsername(userEntity.getUsername())){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("User already exists!");

            return ResponseEntity.ok(responseModel);
        }else{

            BCryptPasswordEncoder bcrypt= new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2B);
            String password = bcrypt.encode(userEntity.getPassword());
            userEntity.setPassword(password);

            userService.save(userEntity);
            return ResponseEntity.ok(userEntity);
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}