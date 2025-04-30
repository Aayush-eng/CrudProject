package com.example.CrudProject.controller;

import com.example.CrudProject.entity.User;
import com.example.CrudProject.entity.Nominee;
import com.example.CrudProject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}/claims")
    public ResponseEntity<User> getUserWithClaims(@PathVariable Long id) {
        Optional<User> user = userService.getUserWithClaims(id);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUserWithNominees(@RequestBody UserWithNomineesRequest request) {
        User savedUser = userService.addUserWithNominees(request.getUser(), request.getNominees());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // Helper class to receive user and nominees in one request
    public static class UserWithNomineesRequest {
        private User user;
        private List<Nominee> nominees;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public List<Nominee> getNominees() {
            return nominees;
        }

        public void setNominees(List<Nominee> nominees) {
            this.nominees = nominees;
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<User>> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}