package org.code.accessrefreshtoken.service;

import org.code.accessrefreshtoken.entity.User;
import org.code.accessrefreshtoken.entity.UserRepository;
import org.code.accessrefreshtoken.security.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenUtils jwtTokenUtils;

    public User addUser(User user) {
        user.setRoles(Collections.singletonList("USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public Iterable<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getByEmail(String username) {
        return Optional.ofNullable(repository.findByUsername(username));
    }

    public String login(User user) {
        User u = repository.findByUsername(user.getUsername());
        if (u != null) {
            if (passwordEncoder.matches(user.getPassword(), u.getPassword())) {
                return jwtTokenUtils.generateToken(u.getEmail());
            }
        }
        return null;
    }

    public String deleteUser(Integer userId) {
        repository.deleteById(userId);
        return "User with id: "+userId+" has been deleted!";
    }
}
