package com.locawork.webapi.dto;

import javax.persistence.Column;
import java.util.Date;

public interface JobDTO {

    int id();

    String title();

    Integer userId();

    String description();

    Integer categoryId();

    Double salary();

    Double latitude();

    Double longitude();

    Date createdAt();

    Date updatedAt();

    boolean isDone();

    String firebase_token();

}