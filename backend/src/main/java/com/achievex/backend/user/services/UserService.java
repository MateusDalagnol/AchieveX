package com.achievex.backend.user.services;

import com.achievex.backend.user.domain.User;
import com.achievex.backend.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private User createUser(String username, String email ) {
        User user = new User(username, email, null, null, new Date(), new Date());
        return userRepository.save(user);
    }

    public User findOrCreateByUsername(String username, String email) {
        Optional<User> user = userRepository.findByUsername(username);

        return user.orElseGet(() -> createUser(username, email));
    }


    public User findOrCreateBySteamId(String steamId) {
        Optional<User> user = userRepository.findBySteamId(steamId);

        return user.orElseGet(() -> createUserFromSteam(steamId));
    }

    private User createUserFromSteam(String steamId) {
        User user = new User("steam_" + steamId,null,null,steamId, new Date(), new Date());
        return userRepository.save(user);
    }
}