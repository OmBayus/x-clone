package com.osntus.xserver.repository;

import com.osntus.xserver.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {
    Optional<Like> findByUsernameAndPostId(String username, int postId);
}
