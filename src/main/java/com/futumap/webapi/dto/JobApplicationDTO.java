package com.futumap.webapi.dto;

import java.util.Date;

public interface JobApplicationDTO {

    Integer id();
    Integer userId();
    String getEmail();
    String jobTitle();
    String jobDescription();
    Double jobSalary();
    Date createdAt();
    Date updatedAt();
    Integer jobId();
}
