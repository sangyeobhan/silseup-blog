package com.sprint.silseup.blog.dto.user;

import com.sprint.silseup.blog.exception.InvalidInputException;

public record RegisterRequest(
        String id,
        String password,
        String email,
        String nickname
) {
    public RegisterRequest {
        if (isNullOrBlank(id)) {
            throw new InvalidInputException("id 입력 필요");
        }
        if (isNullOrBlank(password)) {
            throw new InvalidInputException("password 입력 필요");
        }
        if (isNullOrBlank(email)) {
            throw new InvalidInputException("email 입력 필요");
        }
        if (isNullOrBlank(nickname)) {
            throw new InvalidInputException("닉네임 입력 필요");
        }
    }

    private boolean isNullOrBlank(String input) {
        return input == null || input.isBlank();
    }
}
