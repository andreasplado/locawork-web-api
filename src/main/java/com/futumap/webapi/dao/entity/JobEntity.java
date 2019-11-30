package com.futumap.webapi.dao.entity;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "jobs")
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @TableGenerator(
            name="job",
            table="GENERATOR_TABLE",
            pkColumnName = "key",
            valueColumnName = "next",
            pkColumnValue="course",
            allocationSize=30
    )
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "account_google_id")
    private String accountGoogleId;

    @Column(name = "description")
    private String description;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "job_start_time")
    private String jobStartTime;

    @Column(name = "job_end_time")
    private String jobEndTime;

    @Column(name = "latitude")
    private Double latitude;


    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;


    @Column(name = "regitredAccount")
    private String registredAccount;

    @Column(name = "applyer_contact")
    private String applyerContact;

    public JobEntity() {

    }

    public JobEntity(String title, String description, Double salary, Double longitude, Double latitude) {
        this.setTitle(title);
        this.setDescription(description);
        this.setSalary(salary);
        this.setLongitude(longitude);
        this.setLatitude(latitude);
        this.setCreatedAt(new Date());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @PrePersist
    protected void prePersist() {
        if (this.createdAt == null) createdAt = new Date();
        if (this.updatedAt == null) updatedAt = new Date();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = new Date();
    }

    @PreRemove
    protected void preRemove() {
        this.updatedAt = new Date();
    }

    public Double getLatitude() {
        return latitude;
    }

    private void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    private void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public Double getSalary() {
        return salary;
    }

    private void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getAccountGoogleId() {
        return accountGoogleId;
    }

    public void setAccountGoogleId(String accountGoogleId) {
        this.accountGoogleId = accountGoogleId;
    }

    public String getRegistredAccount() {
        return registredAccount;
    }

    public void setRegistredAccount(String registredAccount) {
        this.registredAccount = registredAccount;
    }

    public String getJobStartTime() {
        return jobStartTime;
    }

    public void setJobStartTime(String jobStartTime) {
        this.jobStartTime = jobStartTime;
    }

    public String getJobEndTime() {
        return jobEndTime;
    }

    public void setJobEndTime(String jobEndTime) {
        this.jobEndTime = jobEndTime;
    }

    public String getApplyerContact() {
        return applyerContact;
    }

    public void setApplyerContact(String applyerContact) {
        this.applyerContact = applyerContact;
    }
}

