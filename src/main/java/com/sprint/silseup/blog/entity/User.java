package com.sprint.silseup.blog.entity;

import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@Getter
@ToString
public class User {
    private String id;
    private String password;
    private String email;
    private String nickname;
    private Instant createdAt;

    public User(String id, String password, String email, String nickname) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.createdAt = Instant.now();
    }
}
