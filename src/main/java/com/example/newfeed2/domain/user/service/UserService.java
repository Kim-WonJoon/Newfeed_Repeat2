package com.example.newfeed2.domain.user.service;

import com.example.newfeed2.auth.dto.AuthUser;
import com.example.newfeed2.common.PasswordEncoder;
import com.example.newfeed2.domain.user.dto.request.UserUpdateRequestDto;
import com.example.newfeed2.domain.user.dto.response.UserDetailResponseDto;
import com.example.newfeed2.domain.user.entity.User;
import com.example.newfeed2.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

//    @Transactional
//    public UserSaveResponseDto save(UserSaveRequestDto userSaveRequestDto) {
//        if (userRepository.existsByEmail(userSaveRequestDto.getEmail())) {
//            throw new IllegalStateException("이미 사용중인 이메일입니다.");
//        }
//
//        String encodedPassword = passwordEncoder.encode(userSaveRequestDto.getPassword());
//
//        User user = new User(userSaveRequestDto.getEmail(), encodedPassword);
//        User savedUser = userRepository.save(user);
//        return new UserSaveResponseDto(savedUser.getId(), savedUser.getEmail(), savedUser.getPassword());
//    }
//
//    public User login(String email, String password) {
//        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("잘못된 이메일이나 비밀번호입니다."));
//
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new IllegalStateException("잘못된 이메일이나 비밀번호입니다.");
//        }
//        return user;
//    }

    @Transactional(readOnly = true)
    public List<UserDetailResponseDto> findAll() {
        List<User> users = userRepository.findAll();

        List<UserDetailResponseDto> userDetailResponseDtos = new ArrayList<>();
        for (User user : users) {
            userDetailResponseDtos.add(new UserDetailResponseDto(user.getId(), user.getEmail()));
        }
        return userDetailResponseDtos;
    }

    @Transactional(readOnly = true)
    public UserDetailResponseDto findByEmail(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new IllegalStateException ("해당 email 사용자를 찾을 수 없음."));
        return new UserDetailResponseDto(user.getId(), user.getEmail());
    }

    @Transactional
    public void update(AuthUser authUser, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findById(authUser.getId()).orElseThrow(() -> new IllegalStateException("해당 사용자를 찾을 수 없음."));
        user.update(userUpdateRequestDto.getPassword());
    }

    @Transactional
    public void deleteById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalStateException("해당 사용자를 찾을 수 없음");
        }
        userRepository.deleteById(userId);
    }
}
