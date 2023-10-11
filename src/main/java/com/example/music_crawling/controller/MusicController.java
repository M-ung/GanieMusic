package com.example.music_crawling.controller;

import com.example.music_crawling.dto.MusicDTO;
import com.example.music_crawling.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MusicController {
    private final MusicService musicService;

    @GetMapping("/music")
    public String Music(Model model) throws Exception{
        List<MusicDTO> musicList = musicService.getMusicDatas();
        musicService.saveMusicDataToDatabase(musicList);
        model.addAttribute("music", musicList);

        return "music";
    }
}
