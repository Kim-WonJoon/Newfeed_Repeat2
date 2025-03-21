package com.example.newfeed2.auth.controller;

import com.example.newfeed2.auth.dto.request.AuthRequestDto;
import com.example.newfeed2.auth.dto.response.AuthResponseDto;
import com.example.newfeed2.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signup")
    public void signup(@RequestBody AuthRequestDto authRequestDto) {
        authService.signup(authRequestDto);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
        return ResponseEntity.ok(authService.login(authRequestDto));
    }
}
