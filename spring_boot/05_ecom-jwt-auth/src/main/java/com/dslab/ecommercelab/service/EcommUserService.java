package com.dslab.ecommercelab.service;

import com.dslab.ecommercelab.entity.User;
import com.dslab.ecommercelab.entity.UserRepository;
import com.dslab.ecommercelab.security.AuthResponse;
import com.dslab.ecommercelab.security.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Optional;

import static io.jsonwebtoken.Jwts.*;

@Service
@Transactional
public class EcommUserService {

    @Autowired
    UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Value("${jwt.secret}")
    private String jwtSecret;

    public User addUser(User user) {
        user.setRoles(Collections.singletonList("USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public Iterable<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getByEmail(String email) {
        return Optional.ofNullable(repository.findByEmail(email));
    }

    public AuthResponse login(User user) {
        User u = repository.findByEmail(user.getEmail());
        if (u != null) {
            if (passwordEncoder.matches(user.getPassword(), u.getPassword())) {
                AuthResponse authResponse = new AuthResponse(jwtUtils.generateJwtToken(u.getEmail()), jwtUtils.generateRefreshToken(u.getEmail()));
                return authResponse;
            }
        }
        return null;
    }

    public AuthResponse reAuth( String refreshToken) throws Exception {
        AuthResponse authResponse = new AuthResponse();
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(refreshToken)
                    .getBody();
            User user = repository.findByEmail(claims.getSubject());
            if (user != null) {
                 authResponse.setAccessToken(jwtUtils.generateJwtToken(user.getEmail()));
                 authResponse.setRefreshToken(jwtUtils.generateRefreshToken(user.getEmail()));
            }
        } catch (Exception e) {
            // Gestisci eventuali errori durante la decodifica del token
            authResponse.setMsg(String.valueOf(e));
            throw new Exception("Errore durante la decodifica del token", e);
        } finally {
            return authResponse;
        }
    }


    public AuthResponse refresh(User user) {
        User u = repository.findByEmail(user.getEmail());
        if (u != null) {
            if (passwordEncoder.matches(user.getPassword(), u.getPassword())) {
                AuthResponse authResponse = new AuthResponse(jwtUtils.generateJwtToken(u.getEmail()), jwtUtils.generateRefreshToken(u.getEmail()));
                return authResponse;
            }
        }
        return null;
    }

    public String deleteUser(Integer userId) {
        repository.deleteById(userId);
        return "User with id: "+userId+" has been deleted!";
    }



}
