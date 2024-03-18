package com.dslab.ecommercelab.service;

import com.dslab.ecommercelab.entity.User;
import com.dslab.ecommercelab.entity.UserRepository;
import com.dslab.ecommercelab.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
public class EcommUserService {

    @Autowired
    UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

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

    public String login(User user) {
        User u = repository.findByEmail(user.getEmail());
        if (u != null) {
            if (passwordEncoder.matches(user.getPassword(), u.getPassword())) {
                return jwtUtils.generateJwtToken(u.getEmail());
            }
        }
        return null;
    }

    public String deleteUser(Integer userId) {
        repository.deleteById(userId);
        return "User with id: "+userId+" has been deleted!";
    }
}
