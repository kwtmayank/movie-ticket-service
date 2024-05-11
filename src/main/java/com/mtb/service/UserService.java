package com.mtb.service;

import com.mtb.entity.User;
import com.mtb.exception.InvalidDataException;
import com.mtb.model.ApplicationConstants;
import com.mtb.model.UserRole;
import com.mtb.model.request.RegisterUser;
import com.mtb.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Value("${app.admin.users}")
    private List<String> adminUserList;

    public User createUser(RegisterUser registerUser) {
        //Adding other mandatory details
        User user = new User();
        user.setFirstName(registerUser.getFirstName());
        user.setLastName(registerUser.getLastName());
        user.setEmail(registerUser.getEmail());
        user.setPassword(registerUser.getPassword());
        user.setUpdateUser(ApplicationConstants.SYSTEM_USER);
        user.setInsertUser(ApplicationConstants.SYSTEM_USER);
        user.setInsertTs(Instant.now());
        user.setUpdateTs(Instant.now());
        String role = adminUserList.contains(user.getEmail()) ? UserRole.ADMIN.name() : UserRole.NORMAL.name();
        user.setRole(role);
        return userRepository.save(user);
    }


    public User getUser(String userId) throws InvalidDataException {
        Optional<User> userDetails = Optional.ofNullable(userRepository.findByEmail(userId)
                .orElseThrow(() -> new InvalidDataException(ApplicationConstants.USER_NOT_FOUND)));
        return userDetails.orElse(null);
    }



}
