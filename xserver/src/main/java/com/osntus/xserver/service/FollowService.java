package com.osntus.xserver.service;

import com.osntus.xserver.dto.BaseResponse;
import com.osntus.xserver.model.Follow;
import com.osntus.xserver.model.User;
import com.osntus.xserver.repository.FollowRepository;
import com.osntus.xserver.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowService(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> followUser(Integer followedId, int userId) {

        Optional<User> user = userRepository.findById(userId);

        Optional<User> followedUser = userRepository.findById(followedId);

        if (user.isEmpty())
            return ResponseEntity.status(404).body(new BaseResponse<>("User can not found!"));
        if (followedUser.isEmpty())
            return ResponseEntity.status(404).body(new BaseResponse<>("User can not found for follow!"));
        if (user.get().getId() == followedUser.get().getId())
            return ResponseEntity.status(406).body(new BaseResponse<>("You can not follow yourself!"));
        if (followRepository.existsFollowByFollowedUserAndUserid(followedUser.get(), user.get()))
            return ResponseEntity.status(406).body(new BaseResponse<>("User Already Following!"));

        var follow = Follow
                .builder()
                .followedTime(LocalDateTime.now())
                .userid(user.get())
                .followedUser(followedUser.get())
                .build();

        return ResponseEntity.ok(new BaseResponse<>("User followed!", followRepository.save(follow)));
    }

    public ResponseEntity<?> getFollows(int userId) {
        List<Follow> follows = new ArrayList<>();
        try {
            for (Follow follow : followRepository.findAll()) {
                if (follow.getUserid().getId() == userId) {
                    follows.add(follow);
                }
            }
            if (follows.isEmpty()) {
                return ResponseEntity.status(404).body(new BaseResponse<>("Follows can not found!"));
            } else {
                return ResponseEntity.ok(new BaseResponse<>("Follows found!", follows));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> getFollowers(int userId) {
        List<Follow> followers = new ArrayList<>();
        try {
            for (Follow follow : followRepository.findAll()) {
                if (follow.getFollowedUser().getId() == userId) {
                    followers.add(follow);
                }
            }
            if (followers.isEmpty()) {
                return ResponseEntity.status(404).body(new BaseResponse<>("Follows can not found!"));
            } else {
                return ResponseEntity.ok(new BaseResponse<>("Follows found!", followers));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> unfollowUser(int id, int userId) {
        if (followRepository.existsFollowByFollowedUserAndUserid(userRepository.findById(id).get(), userRepository.findById(userId).get())) {
            followRepository.delete(followRepository.findByFollowedUserAndUserid(userRepository.findById(id).get(), userRepository.findById(userId).get()));
            return ResponseEntity.ok(new BaseResponse<>("User unfollowed!"));
        } else return ResponseEntity.status(404).body(new BaseResponse<>("Follow can not found!"));
    }

    public Boolean isFollowing(String username, int userId) {

        Optional<User> user = userRepository.findById(userId);
        Optional<User> followedUser = userRepository.findByUsername(username);

        if (user.isPresent() && followedUser.isPresent()) {
            return followRepository.existsFollowByFollowedUserAndUserid(followedUser.get(), user.get());
        }
        return false;
    }
}
