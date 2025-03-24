package com.sprint.silseup.blog.repository;

import com.sprint.silseup.blog.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class UserRepositoryImpl extends AbstractFileRepository<Map<String, User>> implements UserRepository {
    private Map<String, User> data;

    public UserRepositoryImpl() {
        super("data", User.class.getSimpleName()+".ser");
        this.data = loadData();
    }

    @Override
    protected Map<String, User> getEmptyData() {
        return new HashMap<>();
    }

    @Override
    public User save(User user) {
        data.put(user.getId(), user);
        saveData(data);
        return user;
    }

    @Override
    public boolean existsById(String id) {
        return data.containsKey(id);
    }
}
