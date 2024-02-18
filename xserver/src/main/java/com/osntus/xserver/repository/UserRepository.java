package com.osntus.xserver.repository;

import com.osntus.xserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

    Optional<User> findById(Integer id);

    boolean existsByUsername(String username);
}