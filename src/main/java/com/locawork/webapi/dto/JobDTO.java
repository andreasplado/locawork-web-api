package com.locawork.webapi.dto;

import javax.persistence.Column;
import java.util.Date;

public interface JobDTO {

    Integer id();

    String title();

    Integer userId();

    String description();

    Integer categoryId();

    Double salary();

    Double latitude();

    Double longitude();

    Date createdAt();

    Date updatedAt();

    Boolean isDone();

    String firebase_token();

}
