package com.sprint.silseup.blog.dto;

public record LoginResponse(
        boolean success,
        String token
) {
}
