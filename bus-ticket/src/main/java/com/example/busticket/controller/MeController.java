package com.example.busticket.controller;

import com.example.busticket.dto.TopUpDto;
import com.example.busticket.dto.UserDto;
import com.example.busticket.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me")
public class @startuml
actor Client
actor Server

Client -> Server : POST /login {user, pass}
Server --> Client : 200 {accessToken,refreshToken}

Client -> Server : GET /resource\nAuthorization: Bearer accessToken
alt token valido
    Server --> Client : 200 {dati}
else token scaduto
    Client -> Server : POST /refresh {refreshToken}
    Server --> Client : 200 {newAccessToken}
    Client -> Server : GET /resource\nAuthorization: Bearer newAccessToken
    Server --> Client : 200 {dati}
end

@enduml
MeController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<UserDto> me() {
        return ResponseEntity.ok(userService.getCurrentUserDto());
    }

    @PatchMapping("/credit/toup")
    public ResponseEntity<UserDto> topUp(@Valid @RequestBody TopUpDto dto) {
        return ResponseEntity.ok(userService.topUpCurrentUser(dto));
    }
}
