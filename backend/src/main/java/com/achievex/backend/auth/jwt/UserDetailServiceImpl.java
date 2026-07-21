package com.achievex.backend.auth.jwt;

import com.achievex.backend.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userID) throws UsernameNotFoundException {
        return userRepository.findById(UUID.fromString(userID)).map(UserPrincipal::new).orElseThrow(() -> new UsernameNotFoundException("User not found: " + userID));

    }
}