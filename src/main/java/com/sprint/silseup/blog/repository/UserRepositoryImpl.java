package com.sprint.silseup.blog.repository;

import com.sprint.silseup.blog.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepositoryImpl extends AbstractFileRepository<Map<String, User>> implements UserRepository {
    private final Map<String, User> data;

    public UserRepositoryImpl(@Value("${blog.repository.file-directory}") String directory) {
        super(directory, User.class.getSimpleName()+".ser");
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

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(data.get(id));
    }
}
