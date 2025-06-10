package com.app.api.controller;

import com.app.api.dto.AuthRequestDto;
import com.app.domain.model.AuthResponse;
import com.app.domain.model.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(path = "/login", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    public ResponseEntity<AuthResponse> receiveFormData(AuthRequestDto authRequestDto, HttpServletResponse response) {
        Optional<AuthResponse> authResponse = authenticationService.authenticate(authRequestDto.getUsername(), authRequestDto.getPassword(), response);

        return authResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshAccessToken(HttpServletRequest request, HttpServletResponse response) {
        Optional<AuthResponse> authResponse = authenticationService.refreshAccessToken(request, response);

        if (authResponse.isPresent()) {
            return ResponseEntity.ok(authResponse);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token expired");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        authenticationService.logout(request);

        // Expire cookie
        ResponseCookie cookie = ResponseCookie.from("refreshToken", "")
                .path("/auth/refresh-token")
                .httpOnly(true)
                .secure(true)
                .maxAge(0)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok("Logged out successfully");
    }
}


