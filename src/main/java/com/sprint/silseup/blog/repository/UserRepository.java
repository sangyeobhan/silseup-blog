package com.sprint.silseup.blog.repository;

import com.sprint.silseup.blog.entity.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    boolean existsById(String id);
    Optional<User> findById(String id);
}
