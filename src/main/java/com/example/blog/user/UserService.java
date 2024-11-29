package com.example.blog.user;

import com.example.blog._core.error.ex.Exception400;
import com.example.blog._core.error.ex.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User 로그인(UserRequest.LoginDTO loginDTO) {
        User userPs = userRepository.findByUsername(loginDTO.getUsername());
        if (!userPs.getPassword().equals(loginDTO.getPassword())) {
            new Exception400("올바른 비밀번호가 아닙니다.");
        }
        return userPs;
    }
}
