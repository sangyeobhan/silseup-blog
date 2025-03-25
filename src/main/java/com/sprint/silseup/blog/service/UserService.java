package com.sprint.silseup.blog.service;

import com.sprint.silseup.blog.dto.LoginRequest;
import com.sprint.silseup.blog.dto.RegisterRequest;

public interface UserService {
    void register(RegisterRequest request);
    String login(LoginRequest request);
}
