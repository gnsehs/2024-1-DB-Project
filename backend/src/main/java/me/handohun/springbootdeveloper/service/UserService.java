package me.handohun.springbootdeveloper.service;


import lombok.RequiredArgsConstructor;
import me.handohun.springbootdeveloper.config.jwt.TokenProvider;
import me.handohun.springbootdeveloper.domain.User;
import me.handohun.springbootdeveloper.dto.AddUserRequest;
import me.handohun.springbootdeveloper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .username(dto.getUsername())
                .password(bCryptPasswordEncoder.encode(dto.getPassword())) // 패스워드 암호화
                .build()).getId();
    }

    public String login(AddUserRequest user) {
        User user1 = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!(bCryptPasswordEncoder.matches(user.getPassword(), user1.getPassword()))) {
            throw new IllegalArgumentException("Wrong PassWord");


        }
        return tokenProvider.generateToken(user1, Duration.ofHours(1));
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}

