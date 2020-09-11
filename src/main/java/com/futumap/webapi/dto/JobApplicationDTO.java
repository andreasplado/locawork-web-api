package com.futumap.webapi.dto;

import java.util.Date;

public class JobApplicationDTO {

    private Integer id;
    private boolean isApproved;

    private Integer userId;
    private String email;
    private String jobTitle;
    private String jobDescription;
    private Double jobSalary;
    private Date createdAt;
    private Date updatedAt;

    public JobApplicationDTO(Integer id, boolean isApproved, String jobTitle,
                             String jobDescription, Double salary, Date createdAt, Date updatedAt) {
        this.jobDescription = jobDescription;
        this.jobSalary = salary;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.id = id;
        this.isApproved = isApproved;
        this.jobTitle = jobTitle;

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Double getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(Double jobSalary) {
        this.jobSalary = jobSalary;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
