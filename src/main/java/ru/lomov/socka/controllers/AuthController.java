package ru.lomov.socka.controllers;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lomov.socka.entities.AppUser;
import ru.lomov.socka.services.AuthService;
import ru.lomov.socka.utils.ChangePasswordRequest;
import ru.lomov.socka.utils.LoginRequest;
import ru.lomov.socka.utils.LoginResponse;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@RequestBody AppUser user) {
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        AppUser user = authService.login(request.getEmail(), request.getPassword());
        if (user != null) {
            String token = Jwts.builder()
                    .setSubject(user.getUsername())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 день
                    .signWith(SignatureAlgorithm.HS512, "yourSecretKey")
                    .compact();
            return ResponseEntity.ok(new LoginResponse(token, user));
        } else {
            return ResponseEntity.status(401).body(new LoginResponse("Invalid credentials", null));
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity<AppUser> changePassword(@RequestBody ChangePasswordRequest request) {
        return ResponseEntity.ok(authService.changePassword(request.getUserId(), request.getOldPassword(), request.getNewPassword()));
    }

    // Другие методы
}