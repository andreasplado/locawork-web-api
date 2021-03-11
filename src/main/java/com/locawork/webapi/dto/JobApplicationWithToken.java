package com.locawork.webapi.dto;

import javax.persistence.*;
import java.util.Date;

public interface JobApplicationWithToken {

    Integer id();

    String title();

    Integer user_id();

    String description();

    Integer category_id();

    Double salary();

    Double latitude();

    Double longitude();

    Date createdAt();

    Boolean isDone();

    Date updated_at();

    String firebase_token();

}
