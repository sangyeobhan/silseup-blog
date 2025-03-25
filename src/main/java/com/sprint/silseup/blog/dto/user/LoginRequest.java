package com.sprint.silseup.blog.dto.user;

import com.sprint.silseup.blog.exception.InvalidInputException;

public record LoginRequest(
        String id,
        String password
) {
    public LoginRequest {
        if (isNullOrBlank(id)) {
            throw new InvalidInputException("id 입력 필요");
        }
        if (isNullOrBlank(password)) {
            throw new InvalidInputException("password 입력 필요");
        }
    }

    private boolean isNullOrBlank(String input) {
        return input == null || input.isBlank();
    }
}
