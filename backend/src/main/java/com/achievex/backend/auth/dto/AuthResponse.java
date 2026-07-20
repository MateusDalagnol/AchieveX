package com.achievex.backend.auth.dto;

import com.achievex.backend.user.domain.User;

public record AuthResponse( String token, User user) {
}
