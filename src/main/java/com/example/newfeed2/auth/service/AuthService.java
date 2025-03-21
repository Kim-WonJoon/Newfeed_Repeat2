package com.example.newfeed2.auth.service;

import com.example.newfeed2.auth.dto.request.AuthRequestDto;
import com.example.newfeed2.auth.dto.response.AuthResponseDto;
import com.example.newfeed2.common.JwtUtil;
import com.example.newfeed2.common.PasswordEncoder;
import com.example.newfeed2.domain.user.entity.User;
import com.example.newfeed2.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public void signup(AuthRequestDto authRequestDto) {
        // 이미 존재하는 이메일일 때
        if (userRepository.existsByEmail(authRequestDto.getEmail())) {
            throw new IllegalStateException("이미 사용중인 이메일입니다.");
        }

        // 사용 가능한 이메일일 때

        String encodedPassword = passwordEncoder.encode(authRequestDto.getPassword());
        User user = new User(authRequestDto.getEmail(), encodedPassword);
        userRepository.save(user);
    }

    public AuthResponseDto login(AuthRequestDto authRequestDto) {
        // 잘못된 이메일
        User user = userRepository.findByEmail(authRequestDto.getEmail()).orElseThrow(() -> new IllegalStateException("존재하지 않는 이메일입니다."));

        // 잘못된 비밀번호
        String password = authRequestDto.getPassword();
        if (password.equals(user.getPassword())) {
            throw new IllegalStateException("잘못된 비밀번호입니다.");
        }
        // 이메일, 비밀번호가 일치한 경우
        String bearerToken = jwtUtil.createToken(user.getId(), user.getEmail());
        return new AuthResponseDto(bearerToken);
    }
}
