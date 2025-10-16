package com.example.busticket.controller;

import com.example.busticket.dto.TopUpDto;
import com.example.busticket.dto.UserDto;
import com.example.busticket.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ADMIN: elenco utenti
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // ADMIN: dettaglio utente per id (compatibilità Fase 1)
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // ADMIN: ricarica credito per id (compatibilità Fase 1)
    @PatchMapping("/{id}/credit/toup")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> topUpById(@PathVariable Long id, @Valid @RequestBody TopUpDto dto) {
        return ResponseEntity.ok(userService.topUpCreditById(id, dto));
    }
}
