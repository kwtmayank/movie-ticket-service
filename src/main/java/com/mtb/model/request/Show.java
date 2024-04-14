package com.mtb.model.request;

import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public class Show {
    private String showId;
    private String description;
    @NotNull
    private String movieId;
    @NotNull
    private String screenId;
    @NotNull
    private OffsetDateTime showTime;

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public OffsetDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(OffsetDateTime showTime) {
        this.showTime = showTime;
    }

    @Override
    public String toString() {
        return "Show{" +
                "showId='" + showId + '\'' +
                ", description='" + description + '\'' +
                ", movieId='" + movieId + '\'' +
                ", screenId='" + screenId + '\'' +
                ", showTime=" + showTime +
                '}';
    }
}
