package com.locawork.webapi.config;

import com.locawork.webapi.dao.entity.UserEntity;
import com.locawork.webapi.service.UserDataService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDataService userDataService;

    public UserDetailsServiceImpl(UserDataService userDataService) {
        this.userDataService = userDataService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity user = userDataService.findByEmail(email);

        if (user == null) {

            throw new UsernameNotFoundException(email);

        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }
}
