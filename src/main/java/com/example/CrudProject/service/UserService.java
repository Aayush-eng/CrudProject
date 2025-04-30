package com.example.CrudProject.service;

import com.example.CrudProject.entity.User;
import com.example.CrudProject.entity.Nominee;
import com.example.CrudProject.repository.UserRepository;
import com.example.CrudProject.repository.NomineeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final NomineeRepository nomineeRepository;

    public UserService(UserRepository userRepository, NomineeRepository nomineeRepository) {
        this.userRepository = userRepository;
        this.nomineeRepository = nomineeRepository;
    }

    public Optional<User> getUserWithClaims(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User addUserWithNominees(User user, List<Nominee> nominees) {
        User savedUser = userRepository.save(user);

        for (Nominee nominee : nominees) {
            nominee.setUser(savedUser);
            nomineeRepository.save(nominee);
        }

        return savedUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> updateUser(Long id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    user.setUserId(id);  // Ensure ID is set for update
                    return userRepository.save(user);
                });
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}