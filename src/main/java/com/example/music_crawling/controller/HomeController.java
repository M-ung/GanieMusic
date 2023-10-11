package com.example.music_crawling.controller;

import com.example.music_crawling.entitiy.Music;
import com.example.music_crawling.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MusicRepository musicRepository;
    // 기본페이지 요청 메서드
    @GetMapping("/")
    public String Login() {
        return "login"; // => templates 폴더의 index.html을 찾아감
    }
    @GetMapping("/main")
    public String music(Model model) {
        List<String> musicNumbers = Arrays.asList("1", "2", "3"); // 가져올 음악의 번호
        List<Music> recommendedMusic = musicRepository.findByNumberIn(musicNumbers);
        model.addAttribute("recommendedMusic", recommendedMusic);
        return "main";
    }
}