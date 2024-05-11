package com.mtb.model.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public class Show {
    private String showId;
    private String description;
    @NotNull
    private String movieId;
    @NotNull
    private String screenId;
    @NotNull
    @JsonDeserialize(using = LocalDateTimeSerializer.class)
    private Instant showTiming;

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

    public @NotNull Instant getShowTiming() {
        return showTiming;
    }

    public void setShowTiming(Instant showTiming) {
        this.showTiming = showTiming;
    }

    @Override
    public String toString() {
        return "Show{" +
                "showId='" + showId + '\'' +
                ", description='" + description + '\'' +
                ", movieId='" + movieId + '\'' +
                ", screenId='" + screenId + '\'' +
                ", showTime=" + showTiming +
                '}';
    }
}
