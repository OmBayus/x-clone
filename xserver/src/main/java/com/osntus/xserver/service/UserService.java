package com.osntus.xserver.service;

import com.osntus.xserver.dto.BaseResponse;
import com.osntus.xserver.model.User;
import com.osntus.xserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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

    public ResponseEntity<?> getRandomUsers(String username) {
        if (userRepository.findAll().isEmpty()) {
            return ResponseEntity.status(404).body(new BaseResponse<>("There are no users in the database."));
        } else {
            List<User> users = userRepository.findAll();
            Set<User> randomUsers = new HashSet<>();
            Random random = new Random();
            while (randomUsers.size() < 4 && users.size() > 0) {
                int randomIndex = random.nextInt(users.size());
                User user = users.get(randomIndex);
                users.remove(randomIndex);
                if (!user.getUsername().equals(username)) {
                    randomUsers.add(user);
                }
            }
            return ResponseEntity.ok(new BaseResponse<>("Random users found", randomUsers));
        }
    }
}
