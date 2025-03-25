package com.sprint.silseup.blog.service;

import com.sprint.silseup.blog.dto.user.LoginRequest;
import com.sprint.silseup.blog.dto.user.RegisterRequest;
import com.sprint.silseup.blog.entity.User;
import com.sprint.silseup.blog.exception.DuplicateUserException;
import com.sprint.silseup.blog.exception.InvalidInputException;
import com.sprint.silseup.blog.exception.InvalidPasswordException;
import com.sprint.silseup.blog.exception.UserNotFoundException;
import com.sprint.silseup.blog.repository.UserRepository;
import com.sprint.silseup.blog.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

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

    @Override
    public String login(LoginRequest request) {
        User user = userRepository.findById(request.id())
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        if (!BCrypt.checkpw(request.password(), user.getPassword())) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }

        return jwtUtil.generateToken(user.getId());
    }

    private void validateUniqueId(String id) {
        if(userRepository.existsById(id)) {
            throw new DuplicateUserException("중복 아이디");
        }
    }

    private void validateNickname(String nickname) {
        if (nickname.length() < 3 || nickname.length() > 50) {
            throw new InvalidInputException("닉네임 길이는 3~50");
        }
    }

    private void validatePassword(String password) {
        if (password.length() < 12 || password.length() > 50) {
            throw new InvalidInputException("password 길이는 12~50");
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
            throw new InvalidInputException("들어가면 안되는 문자");
        }

        if (letterCount < 2 || digitCount < 2 || specialLetterCount < 2) {
            throw new InvalidInputException("영어/숫자/특수문자 2개씩 있어야 함");
        }
    }

    private void validateEmail(String email) {
        if (email.length() > 100) {
            throw new InvalidInputException("이메일은 100글자 이하");
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidInputException("유효하지 않은 이메일 형식");
        }
    }

    private void validateId(String id) {
        if (id.length() < 6 || id.length() > 30) {
            throw new InvalidInputException("id 길이는 6~30");
        }
    }
}
