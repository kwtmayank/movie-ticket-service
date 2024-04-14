package com.mtb.entity;


import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "BOOKING_DETAILS")
public class BookingDetails {

    @Id
    @Column(name = "BOOKING_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String bookingId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserDetails userId;

    @ManyToOne
    @JoinColumn(name = "SHOW_ID")
    private ShowDetails showId;

    @Column(name = "NUM_OF_SEATS")
    private Integer numberOfSeats;

    @Column(name = "BOOKING_DATE")
    private OffsetDateTime bookingDate;

    @Column(name = "STATUS_ID")
    private String statusId;

    @Column(name = "INSERT_TS", nullable = false)
    private OffsetDateTime insertTs;

    @Column(name = "UPDATE_TS", nullable = false)
    private OffsetDateTime updateTs;

    @Column(name = "UPDATE_USER", nullable = false)
    private String updateUser;


    public BookingDetails() {

    }


    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public UserDetails getUserId() {
        return userId;
    }

    public void setUserId(UserDetails userId) {
        this.userId = userId;
    }

    public ShowDetails getShowId() {
        return showId;
    }

    public void setShowId(ShowDetails showId) {
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

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
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
