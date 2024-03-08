package com.mtb.controller;

import com.mtb.entity.UserDetails;
import com.mtb.model.ApplicationConstants;
import com.mtb.model.Response;
import com.mtb.model.request.LoginRequest;
import com.mtb.model.request.RegisterUserRequest;
import com.mtb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    Response<RegisterUserRequest> registerUser(@RequestBody @Valid RegisterUserRequest request) {
        //Translate the request
        UserDetails user = new UserDetails();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(ApplicationConstants.USER_ROLE_NORMAL);
        //Calling service
        UserDetails createUser = userService.createUser(user);
        //Translate the response
        RegisterUserRequest response = new RegisterUserRequest();
        response.setEmail(createUser.getEmail());
        response.setFirstName(createUser.getFirstName());
        response.setLastName(createUser.getLastName());
        response.setUserId(createUser.getUserId());
        response.setRole(createUser.getRole());
        return new Response<RegisterUserRequest>(null, null, true, response);
    }


    @PostMapping("/login")
    Response<RegisterUserRequest> login(@RequestBody @Valid LoginRequest request) {
        //Translate the request
        UserDetails loggedInUser = userService.findUserAndMatchPassword(request.getEmail(), request.getPassword());
        if (loggedInUser != null) {
            RegisterUserRequest response = new RegisterUserRequest();
            response.setEmail(loggedInUser.getEmail());
            response.setFirstName(loggedInUser.getFirstName());
            response.setLastName(loggedInUser.getLastName());
            response.setUserId(loggedInUser.getUserId());
            return new Response<RegisterUserRequest>(null, null, true, response);
        } else {
            return new Response<RegisterUserRequest>(HttpStatus.NOT_FOUND.name(), "User not found", false, null);
        }
    }

}
