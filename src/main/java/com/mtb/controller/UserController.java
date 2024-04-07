package com.mtb.controller;

import com.mtb.entity.UserDetails;
import com.mtb.model.ApplicationConstants;
import com.mtb.model.Response;
import com.mtb.model.request.RegisterUser;
import com.mtb.model.request.UserLogin;
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
    @ResponseStatus(HttpStatus.CREATED)
    Response<RegisterUser> registerUser(@RequestBody @Valid RegisterUser request) {
        //Translate the request
        UserDetails user = new UserDetails();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
     //   user.setRoleId(ApplicationConstants.USER_ROLE_NORMAL);
        //Calling service
        UserDetails createUser = userService.createUser(user);
        //Translate the response
        RegisterUser response = new RegisterUser();
        response.setEmail(createUser.getEmail());
        response.setFirstName(createUser.getFirstName());
        response.setLastName(createUser.getLastName());
        response.setUserId(createUser.getUserId());
      //  response.setRole(createUser.getRoleId());
        return new Response<RegisterUser>(null, null, true, response);
    }


    @PostMapping("/login")
    Response<RegisterUser> login(@RequestBody @Valid UserLogin request) {
        //Translate the request
        UserDetails loggedInUser = userService.findUserAndMatchPassword(request.getEmail(), request.getPassword());
        if (loggedInUser != null) {
            RegisterUser response = new RegisterUser();
            response.setEmail(loggedInUser.getEmail());
            response.setFirstName(loggedInUser.getFirstName());
            response.setLastName(loggedInUser.getLastName());
            response.setUserId(loggedInUser.getUserId());
            return new Response<RegisterUser>(null, null, true, response);
        } else {
            return new Response<RegisterUser>(HttpStatus.NOT_FOUND.name(), "User not found", false, null);
        }
    }

}
