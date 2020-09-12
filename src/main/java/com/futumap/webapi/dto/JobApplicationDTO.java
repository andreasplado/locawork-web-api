package com.futumap.webapi.dto;

import java.util.Date;

public interface JobApplicationDTO {

    Integer id();
    Integer userId();
    String getAccountEmail();
    String getJobTitle();
    String getJobDescription();
    Double getJobSalary();
    Date getCreatedAt();
    Date getUpdatedAt();
    Integer getJobId();
}
