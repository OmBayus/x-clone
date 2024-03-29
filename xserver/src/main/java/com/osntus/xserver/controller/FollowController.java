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

    @PostMapping("/{id}")
    public ResponseEntity<?> followUser(@PathVariable int id, @AuthenticationPrincipal User user){
        return followService.followUser(id, user.getId());
    }

    @GetMapping("/follows")
    public ResponseEntity<?> getFollows(@AuthenticationPrincipal User user){
        return followService.getFollows(user.getId());
    }

    @GetMapping("/followers")
    public ResponseEntity<?> getFollowers(@AuthenticationPrincipal User user){
        return followService.getFollowers(user.getId());
    }

    @DeleteMapping("/unfollow")
    public ResponseEntity<?> unfollowUser(@RequestParam Integer id, @AuthenticationPrincipal User user){
        return followService.unfollowUser(id, user.getId());
    }

    @GetMapping("/isFollows/{username}")
    public Boolean isFollowing(@PathVariable String username, @AuthenticationPrincipal User user){
        return followService.isFollowing(username, user.getId());
    }
}
