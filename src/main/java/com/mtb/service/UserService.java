package com.mtb.service;

import com.mtb.entity.UserDetails;
import com.mtb.exception.InvalidDataException;
import com.mtb.model.ApplicationConstants;
import com.mtb.model.UserRole;
import com.mtb.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Value("${app.admin.users}")
    private List<String> adminUserList;

    public UserDetails createUser(UserDetails user) {
        //Adding other mandatory details
        user.setUpdateUser(ApplicationConstants.SYSTEM_USER);
        user.setInsertTs(OffsetDateTime.now());
        user.setUpdateTs(OffsetDateTime.now());
        String role = adminUserList.contains(user.getEmail()) ? UserRole.ADMIN.name() : UserRole.NORMAL.name();
        user.setRoleId(role);
        return userRepository.save(user);
    }

    public UserDetails findUserAndMatchPassword(String email, String password) {
        UserDetails user = userRepository.findByEmail(email).orElse(new UserDetails());
        return password.equals(user.getPassword()) ? user : null;
    }

    public UserDetails getUser(String userId) throws InvalidDataException {
        Optional<UserDetails> userDetails = Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow(() -> new InvalidDataException(ApplicationConstants.MOVIE_NOT_FOUND)));
        return userDetails.orElse(null);
    }

}
