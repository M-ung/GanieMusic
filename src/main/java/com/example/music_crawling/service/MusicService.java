package com.example.music_crawling.service;

import com.example.music_crawling.dto.MusicDTO;
import com.example.music_crawling.entitiy.Music;
import com.example.music_crawling.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicService {
    private static String Music_URL = "https://www.genie.co.kr/chart/top200";

    private final MusicRepository musicRepository;

    public void saveMusicDataToDatabase(List<MusicDTO> musicList)  {
        musicRepository.deleteAll();
        for (MusicDTO musicDTO : musicList) {
            Music music = new Music();
            music.setNumber(musicDTO.getNumber());
            music.setImage(musicDTO.getImage());
            music.setTitle(musicDTO.getTitle());
            musicRepository.save(music);
        }
    }
    @PostConstruct
    public List<MusicDTO> getMusicDatas() throws IOException {
        Document document = Jsoup.connect(Music_URL).get();
        List<MusicDTO> musicList = new ArrayList<>();
        Elements songList = document.select(".list-wrap tbody tr");
        int index = 1;

        for (Element song : songList) {
            String number = Integer.toString(index);
            String image = song.select("td .cover img").attr("src");
            String title = song.select(".info").text();
            MusicDTO musicDTO = new MusicDTO();
            musicDTO.setNumber(number);
            musicDTO.setImage(image);
            musicDTO.setTitle(title);
            musicList.add(musicDTO);
            index++;
        }
        return musicList;
    }

}
