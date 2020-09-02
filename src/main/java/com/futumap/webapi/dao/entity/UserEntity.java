package com.futumap.webapi.dao.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {

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

    @Column(name = "password")
    private String password;

    @Column(name = "google_account_id")
    private String googleAccountId;

    @Column(name = "is_account_expired")
    private boolean isAccountExpired;

    @Column(name = "is_account_non_locked")
    private boolean isAccountNonLocked;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String googleAccountId) {
        this.googleAccountId = googleAccountId;
    }

    public void setAccountExpired(boolean isAccountExpired){
        this.isAccountExpired = isAccountExpired;
    }

    public void setAccountLocked(boolean isAccountNonLocked){
        this.isAccountNonLocked = isAccountNonLocked;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.googleAccountId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public String getGoogleAccountId() {
        return googleAccountId;
    }

    public void setGoogleAccountId(String googleAccountId) {
        this.googleAccountId = googleAccountId;
    }
}