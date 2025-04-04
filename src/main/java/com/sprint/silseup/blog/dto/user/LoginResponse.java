package com.sprint.silseup.blog.dto.user;

public record LoginResponse(
        boolean success,
        String token
) {
}
