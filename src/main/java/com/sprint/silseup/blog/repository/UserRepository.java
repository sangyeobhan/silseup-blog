package com.sprint.silseup.blog.repository;

import com.sprint.silseup.blog.entity.User;

public interface UserRepository {
    User save(User user);
    boolean existsById(String id);
}
