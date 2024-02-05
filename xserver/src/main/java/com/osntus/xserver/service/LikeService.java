package com.osntus.xserver.service;

import com.osntus.xserver.dto.BaseResponse;
import com.osntus.xserver.model.Like;
import com.osntus.xserver.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.osntus.xserver.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

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
            List<String> list = new ArrayList<>();
            for (var like : likes) {
                if (like.postId == postId && like.isLiked)
                    list.add(like.username);
            }
            return ResponseEntity.ok().body(new BaseResponse<>(Integer.toString(count), list));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new BaseResponse<>("Error"));
        }
    }

    public ResponseEntity<?> handleLike(String username, int postId) {
        try {
            if (!postRepository.existsById(postId)) {
                return ResponseEntity.status(404).body(new BaseResponse<>("Post does not exist"));
            }
            var like = likeRepository.findByUsernameAndPostId(username, postId);
            if (like.isPresent()) {
                like.get().setLiked(!like.get().isLiked);
                likeRepository.save(like.get());
                var post = postRepository.findById(postId).get();

                var res = ResponseEntity.ok().body(new BaseResponse<>("Liked"));
                if (like.get().isLiked) {
                    post.setLikes(post.getLikes() + 1);
                    res = ResponseEntity.ok().body(new BaseResponse<>("Liked"));
                } else {
                    post.setLikes(post.getLikes() - 1);
                    res = ResponseEntity.ok().body(new BaseResponse<>("Disliked"));
                }
                postRepository.save(post);
                return res;
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
                return ResponseEntity.ok().body(new BaseResponse<>("Liked"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new BaseResponse<>("Error"));
        }
    }
}
