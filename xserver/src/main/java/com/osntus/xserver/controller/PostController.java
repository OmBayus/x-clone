package com.osntus.xserver.controller;

import com.osntus.xserver.dto.PostRequest;
import com.osntus.xserver.model.User;
import com.osntus.xserver.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("app/v1/posting")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/")
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest, @AuthenticationPrincipal User user) {
        try {
            return postService.createPost(postRequest.getText(), user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable int postId) {
        try {
            return postService.deletePost(postId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable int postId, @RequestBody PostRequest postRequest) {
        try {
            return postService.updatePost(postId, postRequest.getText());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPost(@PathVariable int postId) {
        try {
            return ResponseEntity.ok(postService.getPost(postId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
