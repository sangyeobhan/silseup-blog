package com.sprint.silseup.blog.dto;

import lombok.Data;
import lombok.NonNull;

import java.time.Instant;

public record SignupRequest(
        @NonNull String id,
        @NonNull String password,
        @NonNull String email,
        @NonNull String nickname
) {
}
