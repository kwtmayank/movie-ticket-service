package com.mtb.entity;


import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "THEATRE_DETAILS")
public class TheatreDetails {

    @Id
    @Column(name = "THEATRE_CODE", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String theatreCode;

    @Column(name = "NAME", nullable = false)
    private String theatreName;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "INSERT_TS", nullable = false)
    private OffsetDateTime insertTs;

    @Column(name = "UPDATE_TS", nullable = false)
    private OffsetDateTime updateTs;

    @Column(name = "UPDATE_USER", nullable = false)
    private String updateUser;



    public TheatreDetails() {

    }

    public String getTheatreCode() {
        return theatreCode;
    }

    public void setTheatreCode(String theatreCode) {
        this.theatreCode = theatreCode;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
