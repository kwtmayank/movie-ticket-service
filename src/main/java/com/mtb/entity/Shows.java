package com.mtb.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
@Document
public class Shows {

    @Id
    private String showId;
    
    private String screenId;
    
    private String movieId;
    
    private Number availableSeats;
    
    private Instant showTiming;
    
    private Instant insertTs;
    
    private Instant updateTs;
    
    private String updateUser;

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public Number getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Number availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Instant getShowTiming() {
        return showTiming;
    }

    public void setShowTiming(Instant showTiming) {
        this.showTiming = showTiming;
    }

    public Instant getInsertTs() {
        return insertTs;
    }

    public void setInsertTs(Instant insertTs) {
        this.insertTs = insertTs;
    }

    public Instant getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(Instant updateTs) {
        this.updateTs = updateTs;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
