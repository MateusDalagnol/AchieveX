package com.achievex.backend.user.controller;

import com.achievex.backend.auth.jwt.UserPrincipal;
import com.achievex.backend.user.dto.CreateUserRequest;
import com.achievex.backend.user.domain.User;
import com.achievex.backend.user.dto.UpdateUserRequest;
import com.achievex.backend.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/api/v1/users/me")
    public ResponseEntity<User> getMe(@AuthenticationPrincipal UserPrincipal user) {
        return ResponseEntity.status(HttpStatus.OK).body(user.getUser());
    }

    @PutMapping("/api/v1/users/me")
    public ResponseEntity<User> updateMe(@AuthenticationPrincipal UserPrincipal principal, @RequestBody @Valid UpdateUserRequest request) {
        User updatedUser = userService.updateUsername(principal.getUser(), request.username());
        return ResponseEntity.ok(updatedUser);
    }
}