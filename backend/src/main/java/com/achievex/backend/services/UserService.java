package com.achievex.backend.services;

import com.achievex.backend.dtos.CreateUserRequest;
import com.achievex.backend.models.User;
import com.achievex.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(CreateUserRequest request) {
        User user = new User(request.username(), request.email(), null, "124235", null, null);
        return userRepository.save(user);
    }
}