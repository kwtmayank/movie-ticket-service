package com.mtb.controller;

import com.mtb.entity.Bookings;
import com.mtb.exception.InvalidDataException;
import com.mtb.model.ApplicationConstants;
import com.mtb.model.Response;
import com.mtb.model.request.Booking;
import com.mtb.service.BookingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/booking")
public class BookingController {
    Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    BookingService bookingService;

    @PostMapping
    ResponseEntity<Response<Bookings>> createBooking(@RequestBody @Valid Booking request) throws InvalidDataException {
        logger.info("Request received to create booking {}", request.toString());
        //Calling the service
        Bookings bookings = bookingService.createBooking(request);
        //Handling response
        logger.info("Request completed to create booking with {}", bookings.getBookingId());
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.BOOKING_CREATED, true, bookings));
    }

    @GetMapping("/{bookingId}")
    ResponseEntity<Response<Bookings>> getBooking(@PathVariable @NotNull String bookingId) throws InvalidDataException {
        logger.info("Request received to get booking with id {}", bookingId);
        //Calling the service
        Bookings bookings = bookingService.getBooking(bookingId);
        //Handling response
        logger.info("Request completed to get show with id {}", bookingId);
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.BOOKING_RETRIEVED, true, bookings));
    }

    @GetMapping
    ResponseEntity<Response<List<Bookings>>> getAllBookings() {
        logger.info("Request received to get all bookings");
        //Calling the service
        List<Bookings> bookings = bookingService.getAllBookings();
        //Handling response
        logger.info("Request completed to get all bookings");
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.BOOKING_RETRIEVED, true, bookings));
    }


    @PutMapping
    ResponseEntity<Response<Bookings>> updateBooking(@RequestBody @Valid Booking booking) throws InvalidDataException {
        logger.info("Request received to update booking {}", booking.getBookingId());
        if (booking.getBookingId() == null || booking.getBookingId().isBlank()) {
            throw new InvalidDataException(ApplicationConstants.INVALID_BOOKING);
        }
        //Calling the service
        Bookings updatedShow = bookingService.updateBooking(booking);
        //Handling response
        logger.info("Request completed to update booking {}", booking.getBookingId());
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.BOOKING_UPDATED, true, updatedShow));
    }

    @DeleteMapping("/{bookingId}")
    ResponseEntity<Response<String>> deleteBooking(@PathVariable @NotNull String bookingId) throws InvalidDataException {
        logger.info("Request received to delete booking with id {}", bookingId);
        //Calling the service
        bookingService.deleteBooking(bookingId);
        //Handling response
        logger.info("Request completed to delete booking with id {}", bookingId);
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.BOOKING_DELETED, true, null));
    }

}
