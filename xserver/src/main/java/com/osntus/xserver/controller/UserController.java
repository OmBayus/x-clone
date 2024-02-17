package com.osntus.xserver.controller;

import com.osntus.xserver.model.User;
import com.osntus.xserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("app/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        try {
            return userService.getUser(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        try {
            return userService.getAllUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/suggestion")
    public ResponseEntity<?> getSuggestions(@AuthenticationPrincipal User user) {
        try {
            return userService.getRandomUsers(user.getUsername());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/stats/{username}")
    public ResponseEntity<?> getUserStats(@PathVariable String username) {
        try {
            return ResponseEntity.ok(userService.getUserStats(username));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
