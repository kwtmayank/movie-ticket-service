package com.mtb.controller;

import com.mtb.entity.User;
import com.mtb.exception.InvalidDataException;
import com.mtb.model.ApplicationConstants;
import com.mtb.model.Response;
import com.mtb.model.request.LoginRequest;
import com.mtb.model.request.RegisterUser;
import com.mtb.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    ResponseEntity<Response<User>> registerUser(@RequestBody @Valid RegisterUser request) throws InvalidDataException {
        logger.info("Request received to create user {}", request.toString());
        //Check if user exist
        User existingUser = userService.getUser(request.getEmail());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>("400", ApplicationConstants.USER_ALREADY_EXIST, false, null));
        }
        //Calling service
        User createUser = userService.createUser(request);
        //Translate the response
        createUser.setPassword(null);
        logger.info("Request complete to create user with {}", createUser.getEmail());
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.USER_CREATED, true, createUser));
    }


    @PostMapping("/login")
    ResponseEntity<Response<User>> login(@RequestBody @Valid LoginRequest request) throws InvalidDataException {
        logger.info("Request received to login for user {}", request.getEmail());
        //Translate the request
        User loggedInUser = userService.getUser(request.getEmail());
        if (loggedInUser.getPassword().equals(request.getPassword())) {
            logger.info("Login Successful for user {}", request.getEmail());
            loggedInUser.setPassword(null);
            return ResponseEntity.ok(new Response<>(null, ApplicationConstants.USER_LOGGED_IN, true, loggedInUser));
        } else {
            logger.error("Login failed for user {}", request.getEmail());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response<>(String.valueOf(HttpStatus.NOT_FOUND.value()), ApplicationConstants.USER_LOGIN_FAILED, false, null));
        }
    }

}
