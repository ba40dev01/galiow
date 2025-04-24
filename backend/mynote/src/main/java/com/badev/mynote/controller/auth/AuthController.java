package com.badev.mynote.controller.auth;


import com.badev.mynote.dto.LoginRequest;
import com.badev.mynote.dto.RegisterRequest;
import com.badev.mynote.service.Authentification.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
       return authService.login(request);
    }

    @PostMapping("/register")
    public ResponseEntity<?> Register(@RequestBody RegisterRequest request) {
       return authService.register(request);
    }

}