package com.achievex.backend.user.dto;

import jakarta.validation.constraints.*;

public record CreateUserRequest(@NotBlank @Size(min = 3, max = 30)
                                @Pattern(regexp = "^[a-zA-Z0-9_]+$")String username, @NotNull @Email String email) {}
