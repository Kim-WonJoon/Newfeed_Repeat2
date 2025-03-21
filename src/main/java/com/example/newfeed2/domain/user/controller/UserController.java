package com.example.newfeed2.domain.user.controller;

import com.example.newfeed2.auth.annotation.Auth;
import com.example.newfeed2.auth.dto.AuthUser;
import com.example.newfeed2.domain.user.dto.request.UserUpdateRequestDto;
import com.example.newfeed2.domain.user.dto.response.UserDetailResponseDto;
import com.example.newfeed2.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @PostMapping("/users")
//    public ResponseEntity<UserSaveResponseDto> save(@RequestBody UserSaveRequestDto userSaveRequestDto) {
//        return ResponseEntity.ok(userService.save(userSaveRequestDto));
//    }
//
//    @PostMapping("/users")
//    public ResponseEntity<UserSaveResponseDto> login(@RequestBody UserSaveRequestDto userSaveRequestDto) {
//        User user = userService.login(userSaveRequestDto.getEmail(), userSaveRequestDto.getPassword());
//        return ResponseEntity.ok(new UserSaveResponseDto(user.getId(), user.getEmail(), user.getPassword()));
//    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDetailResponseDto>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/users/{userEmail}")
    public ResponseEntity<UserDetailResponseDto> getOne(@PathVariable String userEmail) {
        return ResponseEntity.ok(userService.findByEmail(userEmail));
    }

    @PutMapping("/users/")
    public void update(@Auth AuthUser authUser, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        userService.update(authUser, userUpdateRequestDto);
    }

    @DeleteMapping("/users/{userId}")
    public void delete(@PathVariable Long userId) {
        userService.deleteById(userId);
    }
}
