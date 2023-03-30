package com.dh.usersservice.controller;

import com.dh.usersservice.model.User;
import com.dh.usersservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> findUserById(@PathVariable String userId){
        return ResponseEntity.ok().body(userService.findUserById(userId));
    }

    @PostMapping
    public ResponseEntity<?> saveUser (@RequestBody User user) {
        return ResponseEntity.status(201).body(userService.saveUser(user));
    }
}
