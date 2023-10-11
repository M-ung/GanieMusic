package com.example.music_crawling.repository;

import com.example.music_crawling.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmail(String userEmail);
    Optional<User> findByUserPhone(String userPhone);
}
