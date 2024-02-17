package com.osntus.xserver.repository;

import com.osntus.xserver.model.Follow;
import com.osntus.xserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FollowRepository extends JpaRepository<Follow, String> {
    boolean existsFollowByFollowedUserAndUserid(User followeduserid, User userid);

    Follow findByFollowedUserAndUserid(User user, User user1);

    Long countByFollowedUser(User user);

    Long countByUserid(User user);
}
