package com.achievex.backend.user.controller;

import com.achievex.backend.user.dto.CreateUserRequest;
import com.achievex.backend.user.domain.User;
import com.achievex.backend.user.services.UserService;
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
        User createdUser = userService.findOrCreateByUsername(request.username(), request.email());
        return ResponseEntity.status(HttpStatus.OK).body(createdUser);
    }
}