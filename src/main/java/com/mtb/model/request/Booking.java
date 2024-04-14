package com.mtb.model.request;

import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public class Booking {
    private String bookingId;
    @NotNull
    private String userId;
    @NotNull
    private String showId;
    @NotNull
    private Integer numberOfSeats;
    @NotNull
    private OffsetDateTime bookingDate;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public OffsetDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(OffsetDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", userId='" + userId + '\'' +
                ", showId='" + showId + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", bookingDate=" + bookingDate +
                '}';
    }
}
