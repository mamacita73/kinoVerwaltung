package com.kino.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if ("test@example.com".equals(request.getEmail()) && "passwort123".equals(request.getPassword())) {
            return ResponseEntity.ok(Map.of("success", true, "token", "123456"));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("success", false));
    }

    public static class LoginRequest {
        public String email;
        public String password;

        public String getPassword() {
            return password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}