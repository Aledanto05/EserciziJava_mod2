package com.example.busticket.service;

import com.example.busticket.dto.TopUpDto;
import com.example.busticket.dto.UserDto;
import com.example.busticket.entity.User;
import com.example.busticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ADMIN
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // ADMIN (compatibilità Fase 1)
    public UserDto getUserById(Long id) {
        User u = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDto(u);
    }

    // ADMIN (compatibilità Fase 1)
    @Transactional
    public UserDto topUpCreditById(Long id, TopUpDto dto) {
        validateTopUp(dto);
        User u = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        u.setCredit(u.getCredit().add(dto.getAmount()));
        return convertToDto(u);
    }

    // Autenticato
    public UserDto getCurrentUserDto() {
        User current = getCurrentUser();
        return convertToDto(current);
    }

    // Autenticato
    @Transactional
    public UserDto topUpCurrentUser(TopUpDto dto) {
        validateTopUp(dto);
        User current = getCurrentUser();
        current.setCredit(current.getCredit().add(dto.getAmount()));
        return convertToDto(current);
    }

    private void validateTopUp(TopUpDto dto) {
        if (dto.getAmount() == null || dto.getAmount().compareTo(new BigDecimal("0.00")) <= 0) {
            throw new IllegalArgumentException("Importo ricarica non valido");
        }
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            throw new RuntimeException("Non autenticato");
        }
        String email = auth.getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    private UserDto convertToDto(User u) {
        UserDto dto = new UserDto();
        dto.setId(u.getId());
        dto.setEmail(u.getEmail());
        dto.setCredit(u.getCredit());
        return dto;
    }
}
