package com.achievex.backend.controllers;

import com.achievex.backend.dtos.CreateUserRequest;
import com.achievex.backend.models.User;
import com.achievex.backend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/users")
    public ResponseEntity<User> createUser(@RequestBody @Valid CreateUserRequest request) {
        User createdUser = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}