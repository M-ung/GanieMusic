package com.example.music_crawling.repository;

import com.example.music_crawling.entitiy.Music;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music, Long> {
    void deleteAll();
    List<Music> findByNumberIn(List<String> numbers);
}
