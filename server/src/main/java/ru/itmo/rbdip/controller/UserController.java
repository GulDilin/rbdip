package ru.itmo.rbdip.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.rbdip.service.UserService;

@CrossOrigin(origins = "**", maxAge = 3600)
@RequestMapping("/api/auth")
@RestController
public class UserController {

    public UserController(UserService service) {
        this.service = service;
    }

    UserService service;

    @PostMapping("/registration")
    public ResponseEntity<String> registrationUser(@RequestHeader("Authorization") String authHeader) {
        service.registerUserByHeader(authHeader);
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestHeader("Authorization") String authHeader) {
        service.authorizeUserByHeader(authHeader);
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).build();
    }
}
