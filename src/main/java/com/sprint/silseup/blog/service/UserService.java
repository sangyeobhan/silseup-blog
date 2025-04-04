package com.sprint.silseup.blog.service;

import com.sprint.silseup.blog.dto.user.LoginRequest;
import com.sprint.silseup.blog.dto.user.RegisterRequest;

public interface UserService {
    void register(RegisterRequest request);
    String login(LoginRequest request);
}
