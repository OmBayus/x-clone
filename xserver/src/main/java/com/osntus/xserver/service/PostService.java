package com.osntus.xserver.service;

import com.osntus.xserver.dto.BaseResponse;
import com.osntus.xserver.model.Post;
import com.osntus.xserver.model.User;
import com.osntus.xserver.repository.PostRepository;
import com.osntus.xserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public ResponseEntity<?> createPost(String text, User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(404).body(new BaseResponse<>("User does not exist"));
        } else {
            var post = Post.builder()
                    .text(text)
                    .username(user.getUsername())
                    .name(user.getName())
                    .date(LocalDateTime.now())
                    .isDeleted(false)
                    .likes(0)
                    .build();
            postRepository.save(post);
            return ResponseEntity.ok().body(new BaseResponse<>("Post created!", post));
        }
    }

    public ResponseEntity<?> deletePost(int postId) {
        if (!postRepository.existsById(postId)) {
            return ResponseEntity.status(404).body(new BaseResponse<>("Post does not exist"));
        } else if (postRepository.findById(postId).get().isDeleted()) {
            return ResponseEntity.status(406).body(new BaseResponse<>("Post already deleted"));
        } else {
            var post = postRepository.findById(postId).get();
            post.setDeleted(true);
            postRepository.save(post);
            return ResponseEntity.ok().body(new BaseResponse<>("Post deleted!"));
        }
    }

    public ResponseEntity<?> updatePost(int postId, String text) {
        if (!postRepository.existsById(postId)) {
            return ResponseEntity.status(404).body(new BaseResponse<>("Post does not exist"));
        }
        else if(postRepository.findById(postId).get().isDeleted()) {
            return ResponseEntity.status(406).body(new BaseResponse<>("Post is deleted"));
        } else {
            var post = postRepository.findById(postId).get();
            post.setText(text);
            postRepository.save(post);
            return ResponseEntity.ok().body(new BaseResponse<>("Post updated!", post));
        }
    }

    public ResponseEntity<?> getPost(int postId) {
        if (!postRepository.existsById(postId)) {
            return ResponseEntity.status(404).body(new BaseResponse<>("Post does not exist"));
        } else if (postRepository.findById(postId).get().isDeleted()) {
            return ResponseEntity.status(406).body(new BaseResponse<>("Post is deleted"));
        }
        else {
            return ResponseEntity.ok().body(new BaseResponse<>("Post found!", postRepository.findById(postId).get()));
        }
    }

    public ResponseEntity<?> getAllPosts(String username) {
        if (!userRepository.existsByUsername(username)) {
            return ResponseEntity.status(404).body(new BaseResponse<>("User does not exist"));
        } else if (postRepository.findAll().isEmpty()) {
            return ResponseEntity.status(404).body(new BaseResponse<>("No posts found"));
        } else {
            List<Post> posts = new ArrayList<>();
            try {
                for (Post post : postRepository.findAll()) {
                    if (Objects.equals(post.getUsername(), username)) {
                        posts.add(post);
                    }
                }
                if (posts.isEmpty()) {
                    return ResponseEntity.status(404).body(new BaseResponse<>("Posts can not found!"));
                } else {
                    return ResponseEntity.ok(new BaseResponse<>("Posts found!", posts));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ResponseEntity<?> getAllPosts() {
        if (postRepository.findAll().isEmpty()) {
            return ResponseEntity.status(404).body(new BaseResponse<>("No posts found"));
        } else {
            return ResponseEntity.ok(new BaseResponse<>("Posts found!", postRepository.findAll()));
        }
    }
}
