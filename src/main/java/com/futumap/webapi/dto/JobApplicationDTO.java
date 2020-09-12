package com.futumap.webapi.dto;

import java.util.Date;

public interface JobApplicationDTO {

    Integer getId();
    Integer getUserId();
    String getEmail();
    String getJobTitle();
    String getJobDescription();
    Double getJobSalary();
    Date getCreatedAt();
    Date getUpdatedAt();
    Integer getJobId();
}
