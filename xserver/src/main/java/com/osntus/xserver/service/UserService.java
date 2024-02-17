package com.osntus.xserver.service;

import com.osntus.xserver.dto.BaseResponse;
import com.osntus.xserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<?> getUser(String username) {
        if (userRepository.existsByUsername(username)) {
            return ResponseEntity.ok(userRepository.findByUsername(username));
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    public ResponseEntity<?> getAllUsers() {
        if (userRepository.findAll().isEmpty()) {
            return ResponseEntity.status(404).body(new BaseResponse<>("There are no users in the database."));
        } else {
            return ResponseEntity.ok(new BaseResponse<>("Users found", userRepository.findAll()));
        }
    }
}
