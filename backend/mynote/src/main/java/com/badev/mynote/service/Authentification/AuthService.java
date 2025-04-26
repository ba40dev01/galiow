package com.badev.mynote.service.Authentification;

import com.badev.mynote.dto.AppUserDto;
import com.badev.mynote.dto.AuthResponse;
import com.badev.mynote.dto.LoginRequest;
import com.badev.mynote.dto.RegisterRequest;
import com.badev.mynote.entity.AppUser.AppUser;
import com.badev.mynote.entity.AppUser.AppUserDetails;
import com.badev.mynote.repository.appUser.AppUserRepository;
import com.badev.mynote.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


    public ResponseEntity<?> login(LoginRequest request) {
        try {
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            var user = (AppUserDetails) authentication.getPrincipal();
            AppUser appUser = appUserRepository.findByUsername(user.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String jwt = tokenService.generateToken(user);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(new AuthResponse(jwt, AppUserDto.from(appUser)));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    public ResponseEntity<?> register(RegisterRequest request) {
        if (appUserRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }


        AppUser newUser = AppUser.builder()
                .email(request.getEmail())
                .phoneNumber(request.getPhone())
                .fullName(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        appUserRepository.save(newUser);

        return ResponseEntity.ok("User registered successfully");
    }

}
