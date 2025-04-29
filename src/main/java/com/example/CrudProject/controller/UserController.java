package com.example.CrudProject.controller;
import com.example.CrudProject.entity.User;
import com.example.CrudProject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
