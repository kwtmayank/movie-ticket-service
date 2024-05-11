package com.mtb.model;

public interface ApplicationConstants {
    String USER_ROLE_NORMAL="NORMAL";
    String USER_ROLE_PRIVILEGE="PRIVILEGE";
    String STATUS_CONFIRM="CNF";
    String STATUS_CANCEL="CAN";

    String SYSTEM_USER = "mts-admin";

    //Success Messages
    String THEATRE_CREATED = "Theatre created successfully";
    String THEATRE_UPDATED = "Theatre updated successfully";
    String THEATRE_DELETED = "Theatre deleted successfully";
    String THEATRE_RETRIEVED = "Theatre retrieved successfully";

    String MOVIE_CREATED = "Movie created successfully";
    String MOVIE_UPDATED = "Movie updated successfully";
    String MOVIE_DELETED = "Movie deleted successfully";
    String MOVIE_RETRIEVED = "Movie retrieved successfully";

    String SCREEN_CREATED = "Screen created successfully";
    String SCREEN_UPDATED = "Screen updated successfully";
    String SCREEN_DELETED = "Screen deleted successfully";
    String SCREEN_RETRIEVED = "Screen retrieved successfully";

    String SHOW_CREATED = "Show created successfully";
    String SHOW_UPDATED = "Show updated successfully";
    String SHOW_DELETED = "Show deleted successfully";
    String SHOW_RETRIEVED = "Show retrieved successfully";

    String BOOKING_CREATED = "Booking created successfully";
    String BOOKING_UPDATED = "Booking updated successfully";
    String BOOKING_DELETED = "Booking deleted successfully";
    String BOOKING_RETRIEVED = "Booking retrieved successfully";

    String USER_CREATED = "User created successfully";
    String USER_LOGGED_IN = "User logged in successfully";
    String USER_LOGIN_FAILED = "Invalid user id or password";
    String USER_ALREADY_EXIST = "User already exist with this email";

    //Error Messages
    String THEATRE_NOT_FOUND = "Theatre not found with the given ID";
    String MOVIE_NOT_FOUND = "Movie not found with the given ID";
    String INVALID_GENRE = "Invalid Genre value";
    String INVALID_SCREEN = "Invalid Screen id";
    String SCREEN_NOT_FOUND = "Screen not found with the given ID";

    String INVALID_SHOW = "Invalid Show id";
    String SHOW_NOT_FOUND = "Show not found with the given ID";

    String INVALID_BOOKING = "Invalid booking id";
    String BOOKING_NOT_FOUND = "Booking not found with the given ID";
    String USER_NOT_FOUND = "User not found with the given ID";
}
