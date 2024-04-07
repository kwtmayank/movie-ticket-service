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
    private Number numberOfSeats;

    @Column(name = "BOOKING_DATE")
    private OffsetDateTime bookingDate;

    @ManyToOne
    @JoinColumn(name = "STATUS_ID")
    private Status statusId;

    @Column(name = "INSERT_TS", nullable = false)
    private OffsetDateTime insertTs;

    @Column(name = "UPDATE_TS", nullable = false)
    private OffsetDateTime updateTs;

    @Column(name = "UPDATE_USER", nullable = false)
    private String updateUser;



    public BookingDetails() {

    }



}
