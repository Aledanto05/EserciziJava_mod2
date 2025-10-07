package org.code.accessrefreshtoken.controller;

import org.code.accessrefreshtoken.security.AuthResponse;
import org.code.accessrefreshtoken.security.BasicAuthRequest;
import org.code.accessrefreshtoken.security.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtils, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtils = jwtTokenUtils;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> authenticate(@RequestBody BasicAuthRequest basicAuthRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(basicAuthRequest.getUsername(), basicAuthRequest.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(basicAuthRequest.getUsername());
        String accessToken = jwtTokenUtils.generateToken(userDetails);
        String refreshToken = jwtTokenUtils.generateRefreshToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }
}
