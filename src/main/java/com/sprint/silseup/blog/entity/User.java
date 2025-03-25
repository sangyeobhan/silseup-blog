package com.sprint.silseup.blog.entity;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;

@Getter
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String id;
    private final String password;
    private final String email;
    private final String nickname;
    private final Instant createdAt;

    public User(String id, String password, String email, String nickname) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.createdAt = Instant.now();
    }
}
