package com.futumap.webapi.dto;

public class JobApplicationDTO {

    private Integer id;
    private Integer userId;
    private Integer jobId;
    private String setEmail;
    private String jobTitle;
    private String jobDescription;
    private Double jobSalary;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSetEmail() {
        return setEmail;
    }

    public void setSetEmail(String setEmail) {
        this.setEmail = setEmail;
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
}
