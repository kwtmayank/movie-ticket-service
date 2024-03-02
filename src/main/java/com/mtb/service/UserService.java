package com.mtb.service;

import com.mtb.entity.Users;
import com.mtb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    public Users findUserAndMatchPassword(String email, String password) {
        Users user = userRepository.findByEmail(email).orElse(new Users());
        return password.equals(user.getPassword()) ? user : null;
    }
}
