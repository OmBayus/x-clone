package com.osntus.xserver.service;

import com.osntus.xserver.model.Like;
import com.osntus.xserver.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.osntus.xserver.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public ResponseEntity<?> getLike(int postId) {
        try {
            if (!postRepository.existsById(postId)) {
                return ResponseEntity.notFound().build();
            }
            var likes = likeRepository.findAll();
            var count = 0;
            for (var like : likes) {
                if (like.postId == postId && like.isLiked)
                    count++;
            }
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<?> handleLike(String username, int postId) {
        try {
            if (!postRepository.existsById(postId)) {
                return ResponseEntity.notFound().build();
            }
            var like = likeRepository.findByUsernameAndPostId(username, postId);
            if (like.isPresent()) {
                like.get().setLiked(!like.get().isLiked);
                likeRepository.save(like.get());
                var post = postRepository.findById(postId).get();
                if (like.get().isLiked) {
                    post.setLikes(post.getLikes() + 1);
                } else {
                    post.setLikes(post.getLikes() - 1);
                }
                postRepository.save(post);
                return ResponseEntity.ok().build();
            } else {
                var newLike = Like.builder()
                        .postId(postId)
                        .username(username)
                        .isLiked(true)
                        .build();
                likeRepository.save(newLike);
                var post = postRepository.findById(postId).get();
                post.setLikes(post.getLikes() + 1);
                postRepository.save(post);
                return ResponseEntity.ok().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
