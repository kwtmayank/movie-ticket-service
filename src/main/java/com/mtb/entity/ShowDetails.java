package com.mtb.entity;


import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "SHOW_DETAILS")
public class ShowDetails {

    @Id
    @Column(name = "SHOW_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String showId;

    @ManyToOne
    @JoinColumn(name = "SCREEN_ID", nullable = false)
    private ScreenDetails screenId;

    @ManyToOne
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    private MovieDetails movieId;

    @Column(name = "AVAILABLE_SEATS", nullable = false)
    private Number availableSeats;

    @Column(name = "TIMING", nullable = false)
    private OffsetDateTime showTiming;

    @Column(name = "INSERT_TS", nullable = false)
    private OffsetDateTime insertTs;

    @Column(name = "UPDATE_TS", nullable = false)
    private OffsetDateTime updateTs;

    @Column(name = "UPDATE_USER", nullable = false)
    private String updateUser;



    public ShowDetails() {

    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public ScreenDetails getScreenId() {
        return screenId;
    }

    public void setScreenId(ScreenDetails screenId) {
        this.screenId = screenId;
    }

    public MovieDetails getMovieId() {
        return movieId;
    }

    public void setMovieId(MovieDetails movieId) {
        this.movieId = movieId;
    }

    public Number getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Number availableSeats) {
        this.availableSeats = availableSeats;
    }

    public OffsetDateTime getShowTiming() {
        return showTiming;
    }

    public void setShowTiming(OffsetDateTime showTiming) {
        this.showTiming = showTiming;
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
