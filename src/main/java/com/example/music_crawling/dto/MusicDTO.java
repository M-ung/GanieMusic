package com.example.music_crawling.dto;

import com.example.music_crawling.entitiy.Music;
import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MusicDTO {
    private Long id;
    private String number;
    private String image;
    private String title;

    public static MusicDTO toMusicDTO(Music music) {
        MusicDTO musicDTO = new MusicDTO();
        musicDTO.setId(music.getId());
        musicDTO.setNumber(music.getNumber());
        musicDTO.setImage(music.getImage());
        musicDTO.setTitle(music.getTitle());
        return musicDTO;
    }
}