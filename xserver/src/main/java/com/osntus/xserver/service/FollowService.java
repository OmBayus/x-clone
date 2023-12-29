package com.osntus.xserver.service;

import com.osntus.xserver.dto.BaseResponse;
import com.osntus.xserver.model.Follow;
import com.osntus.xserver.model.User;
import com.osntus.xserver.repository.FollowRepository;
import com.osntus.xserver.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    //follows a user
    public ResponseEntity<?> followUser(Integer followedId, int userId) {

        Optional<User> user = userRepository.findById(userId);

        Optional<User> followedUser = userRepository.findById(followedId);

        if (user.isEmpty()) return ResponseEntity.status(404).body(new BaseResponse<>("User can not found!"));
        if (followedUser.isEmpty()) return ResponseEntity.status(404).body(new BaseResponse<>("User can not found for follow!"));
        if (user.get().getId() == followedUser.get().getId()) return ResponseEntity.status(406).body(new BaseResponse<>("You can not follow yourself!"));
        if (followRepository.existsFollowByFollowedUserAndUserid(followedUser.get(), user.get())) return ResponseEntity.status(406).body(new BaseResponse<>("User Already Following!"));

        Date date = new Date();
        var follow = Follow
                .builder()
                .followedTime(date)
                .userid(user.get())
                .followedUser(followedUser.get())
                .build();

        return ResponseEntity.ok(new BaseResponse<>("User followed!", followRepository.save(follow)));
    }

    public ResponseEntity<?> getFollows(){
        return null;
    }

    public ResponseEntity<?> getFollowers(){
        return null;
    }
}
