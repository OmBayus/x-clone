package com.osntus.xserver.service;

import com.osntus.xserver.dto.BaseResponse;
import com.osntus.xserver.model.Follow;
import com.osntus.xserver.model.User;
import com.osntus.xserver.repository.FollowRepository;
import com.osntus.xserver.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
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

        if (user.isEmpty()) return ResponseEntity.status(404).body(new BaseResponse<>("User can not found!"));
        if (followedUser.isEmpty()) return ResponseEntity.status(404).body(new BaseResponse<>("User can not found for follow!"));
        if (user.get().getId() == followedUser.get().getId()) return ResponseEntity.status(406).body(new BaseResponse<>("You can not follow yourself!"));
        if (followRepository.existsFollowByFollowedUserAndUserid(followedUser.get(), user.get())) return ResponseEntity.status(406).body(new BaseResponse<>("User Already Following!"));

        var follow = Follow
                .builder()
                .followedTime(LocalDateTime.now())
                .userid(user.get())
                .followedUser(followedUser.get())
                .build();

        return ResponseEntity.ok(new BaseResponse<>("User followed!", followRepository.save(follow)));
    }

    public ResponseEntity<?> getFollows(int userId) {
        try {
            for (Follow follow : followRepository.findAll()) {
                if (follow.getUserid().getId() == userId) {
                    return ResponseEntity.ok(new BaseResponse<>("Follows found!", followRepository.findAll()));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(404).body(new BaseResponse<>("Follows can not found!"));
    }

    public ResponseEntity<?> getFollowers(int userId){
        try {
            for (Follow follow : followRepository.findAll()) {
                if (follow.getFollowedUser().getId() == userId) {
                    return ResponseEntity.ok(new BaseResponse<>("Followers found!", followRepository.findAll()));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(404).body(new BaseResponse<>("Followers can not found!"));
    }

    public ResponseEntity<?> unfollowUser(Integer id, int userId) {
        if (followRepository.existsById(String.valueOf(id)) && followRepository.findById(String.valueOf(id)).get().getUserid().getId() == userId ){
            followRepository.deleteById(String.valueOf(id));
            return ResponseEntity.ok(new BaseResponse<>("User unfollowed!"));
        }
        else return ResponseEntity.status(404).body(new BaseResponse<>("Follow can not found!"));
    }
}
