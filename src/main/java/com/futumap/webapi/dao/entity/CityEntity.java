package com.futumap.webapi.dao.entity;

import org.joda.time.DateTime;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "jobs")
public class CityEntity {

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

    @Column(name = "description")
    private String description;

    @Column(name = "salary")
    private double salary;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    public CityEntity() {

    }

    public CityEntity(String title, String description, double salary, String longitude, String latitude) {
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

    public String getLongitude() {
        return longitude;
    }

    private void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    private void setLatitude(String latitude) {
        this.latitude = latitude;
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

    public double getSalary() {
        return salary;
    }

    private void setSalary(double salary) {
        this.salary = salary;
    }
}

