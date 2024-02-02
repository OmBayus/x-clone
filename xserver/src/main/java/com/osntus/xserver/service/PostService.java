package com.osntus.xserver.service;

import com.osntus.xserver.model.Post;
import com.osntus.xserver.model.User;
import com.osntus.xserver.repository.PostRepository;
import com.osntus.xserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public ResponseEntity<?> createPost(String text, User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            System.out.println("User does not exist");
        } else {
            var post = Post.builder()
                    .text(text)
                    .username(user.getUsername())
                    .date(new Date())
                    .isDeleted(false)
                    .build();
            postRepository.save(post);
            return ResponseEntity.ok().build();
        }
        return null;
    }

    public void deletePost(int postId) {
        if (!postRepository.existsById(postId)) {
            System.out.println("Post does not exist");
        } else {
            var post = postRepository.findById(postId).get();
            post.setDeleted(true);
            postRepository.save(post);
        }
    }

    public void updatePost(int postId, String text) {
        if (!postRepository.existsById(postId)) {
            System.out.println("Post does not exist");
        } else {
            var post = postRepository.findById(postId).get();
            post.setText(text);
            postRepository.save(post);
        }
    }

    public Post getPost(int postId) {
        if (!postRepository.existsById(postId)) {
            System.out.println("Post does not exist");
            return null;
        } else {
            return postRepository.findById(postId).get();
        }
    }
}
