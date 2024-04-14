package com.mtb.service;

import com.mtb.entity.BookingDetails;
import com.mtb.exception.InvalidDataException;
import com.mtb.model.ApplicationConstants;
import com.mtb.model.request.Booking;
import com.mtb.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
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


    public BookingDetails getBooking(String bookingId) throws InvalidDataException {
        Optional<BookingDetails> bookingDetails = Optional.ofNullable(bookingRepository.findById(bookingId)
                .orElseThrow(() -> new InvalidDataException(ApplicationConstants.INVALID_BOOKING)));
        return bookingDetails.orElse(null);
    }

    public BookingDetails createBooking(Booking booking) throws InvalidDataException {
        BookingDetails bookingDetails = new BookingDetails();
        bookingDetails.setBookingDate(booking.getBookingDate());
        bookingDetails.setShowId(showService.getShow(booking.getShowId()));
        bookingDetails.setUserId(userService.getUser(booking.getUserId()));
        bookingDetails.setNumberOfSeats(booking.getNumberOfSeats());
        bookingDetails.setStatusId(ApplicationConstants.STATUS_CONFIRM);
        bookingDetails.setUpdateUser(ApplicationConstants.SYSTEM_USER);
        bookingDetails.setInsertTs(OffsetDateTime.now());
        bookingDetails.setUpdateTs(OffsetDateTime.now());
        return bookingRepository.save(bookingDetails);
    }

    public BookingDetails updateBooking(Booking booking) throws InvalidDataException {
        BookingDetails bookingDetails = this.getBooking(booking.getBookingId());
        bookingDetails.setBookingDate(booking.getBookingDate());
        bookingDetails.setShowId(showService.getShow(booking.getShowId()));
        bookingDetails.setUserId(userService.getUser(booking.getUserId()));
        bookingDetails.setNumberOfSeats(booking.getNumberOfSeats());
        bookingDetails.setStatusId(ApplicationConstants.STATUS_CONFIRM);
        bookingDetails.setUpdateTs(OffsetDateTime.now());
        return bookingRepository.save(bookingDetails);
    }

    public void deleteBooking(String bookingId) throws InvalidDataException {
        bookingRepository.delete(this.getBooking(bookingId));
    }

    public List<BookingDetails> getAllBookings() {
        List<BookingDetails> bookingDetails = new ArrayList<>();
        bookingRepository.findAll().forEach(bookingDetails::add);
        return bookingDetails;
    }


}
