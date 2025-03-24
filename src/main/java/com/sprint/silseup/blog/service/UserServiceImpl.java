package com.sprint.silseup.blog.service;

import com.sprint.silseup.blog.dto.RegisterRequest;
import com.sprint.silseup.blog.entity.User;
import com.sprint.silseup.blog.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$");

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(RegisterRequest request) {
        validateId(request.id());
        validateUniqueId(request.id());
        validateEmail(request.email());
        validatePassword(request.password());
        validateNickname(request.nickname());

        String hashedPassword = BCrypt.hashpw(request.password(), BCrypt.gensalt());

        User user = new User(request.id(), hashedPassword, request.email(), request.nickname());
        userRepository.save(user);
    }

    private void validateUniqueId(String id) {
        if(userRepository.existsById(id)) {
            throw new IllegalArgumentException("중복 아이디");
        }
    }

    private void validateNickname(String nickname) {
        if (isNullOrBlank(nickname)) {
            throw new IllegalArgumentException("닉네임 입력 필요");
        }
        if (nickname.length() < 3 || nickname.length() > 50) {
            throw new IllegalArgumentException("닉네임 길이는 3~50");
        }
    }

    private void validatePassword(String password) {
        if (isNullOrBlank(password)) {
            throw new IllegalArgumentException("password 입력 필요");
        }
        if (password.length() < 12 || password.length() > 50) {
            throw new IllegalArgumentException("password 길이는 12~50");
        }

        validatePasswordComplexity(password);
    }


    private static void validatePasswordComplexity(String password) {
        int letterCount = 0;
        int digitCount = 0;
        int specialLetterCount = 0;

        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                letterCount++;
                continue;
            }
            if (Character.isDigit(c)) {
                digitCount++;
                continue;
            }
            if ("!@#$%^&*".contains(String.valueOf(c))) {
                specialLetterCount++;
                continue;
            }
            throw new IllegalArgumentException("들어가면 안되는 문자");
        }

        if (letterCount < 2 || digitCount < 2 || specialLetterCount < 2) {
            throw new IllegalArgumentException("영어/숫자/특수문자 2개씩 있어야 함");
        }
    }

    private void validateEmail(String email) {
        if (isNullOrBlank(email)) {
            throw new IllegalArgumentException("email 입력 필요");
        }

        if (email.length() > 100) {
            throw new IllegalArgumentException("이메일은 100글자 이하");
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("유효하지 않은 이메일 형식");
        }
    }

    private void validateId(String id) {
        if (isNullOrBlank(id)) {
            throw new IllegalArgumentException("ID 입력 필요");
        }
        if (id.length() < 6 || id.length() > 30) {
            throw new IllegalArgumentException("id 길이는 6~30");
        }
    }

    private boolean isNullOrBlank(String input) {
        return input == null || input.isBlank();
    }
}
