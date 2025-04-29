package com.example.CrudProject.service;

import com.example.CrudProject.entity.User;
import com.example.CrudProject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserWithClaims(Long id) {
        return userRepository.findById(id);
    }
}
