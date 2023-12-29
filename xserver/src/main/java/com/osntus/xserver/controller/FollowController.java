package com.osntus.xserver.controller;

import com.osntus.xserver.model.User;
import com.osntus.xserver.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app/v1/follow")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/")
    public ResponseEntity<?> followUser(@RequestParam Integer id, @AuthenticationPrincipal User user){
        return followService.followUser(id, user.getId());
    }
}
