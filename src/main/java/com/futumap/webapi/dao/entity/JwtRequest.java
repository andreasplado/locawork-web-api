package com.futumap.webapi.dao.entity;

import java.io.Serializable;

public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;
    private String username;
    private String googleAccountId;

    //need default constructor for JSON Parsing
    public JwtRequest() {
    }

    public JwtRequest(String username, String googleAccountId) {
        this.setUsername(username);
        this.setGoogleAccountId(googleAccountId);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGoogleAccountId() {
        return googleAccountId;
    }

    public void setGoogleAccountId(String googleAccountId) {
        this.googleAccountId = googleAccountId;
    }
}