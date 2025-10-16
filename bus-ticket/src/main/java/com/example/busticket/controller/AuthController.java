package com.example.busticket.controller;

import com.example.busticket.dto.JwtAuthResponse;
import com.example.busticket.dto.LoginDto;
import com.example.busticket.dto.RegisterDto;
import com.example.busticket.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Alias legacy /auth mantenuto, ma esponiamo anche /login e /register root
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    // Consegna: POST /login
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> loginRoot(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        return ResponseEntity.ok(new JwtAuthResponse(token));
    }

    // Consegna: POST /register
    @PostMapping("/register")
    public ResponseEntity<JwtAuthResponse> registerRoot(@RequestBody RegisterDto registerDto) {
        String token = authService.register(registerDto);
        return ResponseEntity.ok(new JwtAuthResponse(token));
    }

    // Compatibilità preesistente
    @PostMapping("/auth/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        return ResponseEntity.ok(new JwtAuthResponse(token));
    }

    @PostMapping("/auth/register")
    public ResponseEntity<JwtAuthResponse> register(@RequestBody RegisterDto registerDto) {
        String token = authService.register(registerDto);
        return ResponseEntity.ok(new JwtAuthResponse(token));
    }
}
