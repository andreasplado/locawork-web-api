package com.locawork.webapi.dto;

import javax.persistence.Column;
import java.util.Date;

public interface JobDTO {

    Integer id();

    String title();

    String description();

    Integer user_id();

    Integer category_id();

    Double salary();

    Double latitude();

    Double longitude();

    Date createdAt();

    Date updatedAt();

    String firebase_token();

}
