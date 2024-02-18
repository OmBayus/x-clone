package com.osntus.xserver.repository;

import com.osntus.xserver.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
