package com.badev.mynote.service;

import com.badev.mynote.entity.AppUser.AppUser;
import com.badev.mynote.entity.AppUser.AppUserDetails;
import com.badev.mynote.repository.appUser.AppUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtEncoder encoder;
    private final AppUserRepository appUserRepository;
    private final JwtDecoder decoder;

    private static final String AUTH_HEADER = "Authorization";

    public String generateToken(AppUserDetails user) {
        Instant now = Instant.now();
        String scope = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" ")); // Added space between authorities

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(user.getUsername())
                .claim("scope", scope)
                .build();

        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


    public AppUser parseToken(String token) {
        try {
            Jwt jwt = decoder.decode(token);
            String username = jwt.getSubject();

            AppUser appUser = appUserRepository.findByUsername(username).orElseThrow();
            return appUser;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public AppUser getAppUserFromToken (HttpServletRequest request) throws Exception {
        String authHeader = request.getHeader(AUTH_HEADER);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            return parseToken(token);
        }
        throw new Exception("error");

    }
}