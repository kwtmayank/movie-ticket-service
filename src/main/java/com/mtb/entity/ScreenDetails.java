package com.mtb.entity;


import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "SCREEN_DETAILS")
public class ScreenDetails {

    @Id
    @Column(name = "SCREEN_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String screenId;

    @ManyToOne
    @JoinColumn(name = "THEATRE_ID")
    private TheatreDetails theatreId;

    @Column(name = "CAPACITY")
    private Number capacity;

    @Column(name = "INSERT_TS", nullable = false)
    private OffsetDateTime insertTs;

    @Column(name = "UPDATE_TS", nullable = false)
    private OffsetDateTime updateTs;

    @Column(name = "UPDATE_USER", nullable = false)
    private String updateUser;



    public ScreenDetails() {

    }


    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public TheatreDetails getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(TheatreDetails theatreId) {
        this.theatreId = theatreId;
    }

    public Number getCapacity() {
        return capacity;
    }

    public void setCapacity(Number capacity) {
        this.capacity = capacity;
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
