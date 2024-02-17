package com.osntus.xserver.controller;

import com.osntus.xserver.model.User;
import com.osntus.xserver.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("app/v1/like")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getLike(@PathVariable int postId) {
        if (postId == 0) {
            return ResponseEntity.badRequest().build();
        }
        return likeService.getLike(postId);
    }

    @PostMapping("/{postId}")
    public ResponseEntity<?> createLike(@PathVariable int postId, @AuthenticationPrincipal User user) {
        if (postId == 0) {
            return ResponseEntity.badRequest().build();
        }
        return likeService.handleLike(user.getUsername(), postId);
    }
}
