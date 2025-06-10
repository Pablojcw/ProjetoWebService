package com.app.domain.model;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public interface AuthenticationService {
    Optional<AuthResponse> authenticate(String username, String password, HttpServletResponse response);
    Optional<AuthResponse> refreshAccessToken(HttpServletRequest refreshToken, HttpServletResponse response);
    void logout(HttpServletRequest request);
}
