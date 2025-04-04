package com.sprint.silseup.blog.dto.error;

public record ErrorResponse(
        int statusCode,
        String message
) {
}
