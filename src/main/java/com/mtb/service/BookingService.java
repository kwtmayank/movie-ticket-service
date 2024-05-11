package com.mtb.service;

import com.mtb.entity.Bookings;
import com.mtb.exception.InvalidDataException;
import com.mtb.model.ApplicationConstants;
import com.mtb.model.request.Booking;
import com.mtb.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    Logger logger = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private ShowService showService;


    public Bookings getBooking(String bookingId) throws InvalidDataException {
        Optional<Bookings> bookings = Optional.ofNullable(bookingRepository.findById(bookingId)
                .orElseThrow(() -> new InvalidDataException(ApplicationConstants.INVALID_BOOKING)));
        return bookings.orElse(null);
    }

    public Bookings createBooking(Booking booking) throws InvalidDataException {
        Bookings bookings = new Bookings();
        bookings.setBookingDate(booking.getBookingDate());
        bookings.setShowId(showService.getShow(booking.getShowId()).getShowId());
        bookings.setUserId(userService.getUser(booking.getUserId()).getEmail());
        bookings.setNumberOfSeats(booking.getNumberOfSeats());
        bookings.setStatus(ApplicationConstants.STATUS_CONFIRM);
        bookings.setUpdateUser(ApplicationConstants.SYSTEM_USER);
        bookings.setInsertTs(Instant.now());
        bookings.setUpdateTs(Instant.now());
        return bookingRepository.save(bookings);
    }

    public Bookings updateBooking(Booking booking) throws InvalidDataException {
        Bookings bookings = this.getBooking(booking.getBookingId());
        bookings.setBookingDate(booking.getBookingDate());
        bookings.setShowId(showService.getShow(booking.getShowId()).getShowId());
        bookings.setUserId(userService.getUser(booking.getUserId()).getEmail());
        bookings.setNumberOfSeats(booking.getNumberOfSeats());
        bookings.setStatus(ApplicationConstants.STATUS_CONFIRM);
        bookings.setUpdateTs(Instant.now());
        return bookingRepository.save(bookings);
    }

    public void deleteBooking(String bookingId) throws InvalidDataException {
        bookingRepository.delete(this.getBooking(bookingId));
    }

    public List<Bookings> getAllBookings() {
        List<Bookings> bookings = new ArrayList<>();
        bookingRepository.findAll().forEach(bookings::add);
        return bookings;
    }


}
