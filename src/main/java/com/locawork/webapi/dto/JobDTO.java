package com.locawork.webapi.dto;

import javax.persistence.Column;
import java.util.Date;

public interface JobDTO {

    String title();

    Integer userId();

    String description();

    Integer categoryId();

    Double salary();

    Double latitude();

    Double longitude();

    Date createdAt();

    boolean isDone();

    String firebase_token();

}
