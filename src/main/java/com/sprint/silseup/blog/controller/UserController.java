package com.sprint.silseup.blog.controller;

import com.sprint.silseup.blog.dto.RegisterRequest;
import com.sprint.silseup.blog.dto.RegisterResponse;
import com.sprint.silseup.blog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RegisterResponse(true, "회원 가입이 완료되었습니다."));
    }
}
