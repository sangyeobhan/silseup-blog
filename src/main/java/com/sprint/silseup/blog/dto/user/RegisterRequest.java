package com.sprint.silseup.blog.dto.user;

import lombok.NonNull;

public record RegisterRequest(
        @NonNull String id,
        @NonNull String password,
        @NonNull String email,
        @NonNull String nickname
) {
}
