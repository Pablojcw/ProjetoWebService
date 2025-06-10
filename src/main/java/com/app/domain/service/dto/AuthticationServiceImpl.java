package com.app.domain.service.dto;

import com.app.domain.JwtUtil;
import com.app.domain.model.AuthResponse;
import com.app.domain.model.AuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class AuthticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;


    public AuthticationServiceImpl(AuthenticationManager authenticationManager,
                                   UserDetailsService userDetailsService,
                                   JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Optional<AuthResponse> authenticate(String username, String password, HttpServletResponse response) {
        try {
            if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
                return Optional.empty();
            }

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            final String accessToken = jwtUtil.generateAccessToken(userDetails);
            final String refreshToken = jwtUtil.generateRefreshToken(userDetails);

            setRefreshTokenCookie(response, refreshToken);

            return Optional.of(new AuthResponse(accessToken));
        } catch (BadCredentialsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<AuthResponse> refreshAccessToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            String refreshToken = extractTokenFromCookie(request);
            if (refreshToken == null) {
                return Optional.empty();
            }

            String username = jwtUtil.extractUsername(refreshToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (!jwtUtil.isTokenExpired(refreshToken)) {
                String newAccessToken = jwtUtil.generateAccessToken(userDetails);
                String newRefreshToken = jwtUtil.generateRefreshToken(userDetails);
                setRefreshTokenCookie(response, newRefreshToken);
                return Optional.of(new AuthResponse(newAccessToken));
            }

            return Optional.empty();

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void logout(HttpServletRequest request) {
        String refreshToken = extractTokenFromCookie(request);
        if (refreshToken != null) {
            try {
                Claims refreshClaims = jwtUtil.parseToken(refreshToken);
                String jti = refreshClaims.getId();
//                refreshTokenRepository.findById(jti).ifPresent(token -> {
//                    token.setRevoked(true);
//                    refreshTokenRepository.save(token);
//                });
            } catch (JwtException ignored) {
            }
        }
    }

    private String extractTokenFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("refreshToken")) {
                return cookie.getValue();
            }
        }
        return null;
    }

    private void setRefreshTokenCookie(HttpServletResponse response, String token) {
        ResponseCookie cookie = ResponseCookie.from("refreshToken", token)
                .httpOnly(true)
                .secure(true)
                .path("/auth/refresh-token") // Only accessible by refresh endpoint
                .maxAge(7 * 24 * 60 * 60) // 7 days
                .sameSite("Strict")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
