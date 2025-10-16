package com.example.busticket.service;

import com.example.busticket.dto.LoginDto;
import com.example.busticket.dto.RegisterDto;
import com.example.busticket.entity.Role;
import com.example.busticket.entity.User;
import com.example.busticket.repository.RoleRepository;
import com.example.busticket.repository.UserRepository;
import com.example.busticket.config.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Collections;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        return jwtTokenProvider.generateToken(authentication);
    }

    public String register(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email già registrata");
        }
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        BigDecimal initial = registerDto.getInitialCredit() != null ? registerDto.getInitialCredit() : BigDecimal.ZERO;
        if (initial.signum() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credito iniziale non può essere negativo");
        }
        user.setCredit(initial);

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> roleRepository.save(new Role(null, "ROLE_USER")));
        user.setRoles(Collections.singleton(userRole));

        userRepository.save(user);

        // login immediato post-registrazione
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerDto.getEmail(), registerDto.getPassword()));
        return jwtTokenProvider.generateToken(authentication);
    }
}
