package com.mtb.model;

public interface ApplicationConstants {
    String USER_ROLE_NORMAL="NORMAL";
    String USER_ROLE_PRIVILEGE="PRIVILEGE";

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

    //Error Messages
    String THEATRE_NOT_FOUND = "Theatre not found with the given ID";
    String MOVIE_NOT_FOUND = "Movie not found with the given ID";
    String INVALID_GENRE = "Invalid Genre value";
}
