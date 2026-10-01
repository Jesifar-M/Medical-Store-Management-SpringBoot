package com.example.medicalstore.service;

import com.example.medicalstore.entity.User;
import com.example.medicalstore.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signup(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return null;
        }

        return userRepository.save(user);
    }

    public User login(String email, String password) {

        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }
}