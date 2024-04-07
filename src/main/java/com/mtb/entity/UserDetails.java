package com.mtb.entity;


import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "USER_DETAILS")
public class UserDetails {
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", nullable = false)
    private String email;
    @Id
    @Column(name = "USER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @Column(name = "PASSWORD_TEXT", nullable = false)
    private String password;


    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private UserType roleId;

    @Column(name = "INSERT_TS", nullable = false)
    private OffsetDateTime insertTs;

    @Column(name = "UPDATE_TS", nullable = false)
    private OffsetDateTime updateTs;

    @Column(name = "UPDATE_USER", nullable = false)
    private String updateUser;


    public UserDetails() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getRoleId() {
        return roleId;
    }

    public void setRoleId(UserType roleId) {
        this.roleId = roleId;
    }

    public OffsetDateTime getInsertTs() {
        return insertTs;
    }

    public void setInsertTs(OffsetDateTime insertTs) {
        this.insertTs = insertTs;
    }

    public OffsetDateTime getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(OffsetDateTime updateTs) {
        this.updateTs = updateTs;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
