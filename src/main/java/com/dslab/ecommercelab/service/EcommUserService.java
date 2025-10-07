package com.dslab.ecommercelab.service;

import com.dslab.ecommercelab.entity.User;
import com.dslab.ecommercelab.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    PasswordEncoder encoder;

    public User addUser(User user) {
        user.setRoles(Collections.singletonList("USER"));
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public Iterable<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getByEmail(String email) {
        return Optional.ofNullable(repository.findByEmail(email));
    }

    public String deleteUser(Integer userId) {
        repository.deleteById(userId);
        return "User with id: "+userId+" has been deleted!";
    }
}
