package com.example.music_crawling.entitiy;


import com.example.music_crawling.dto.MusicDTO;
import com.example.music_crawling.dto.UserDTO;
import lombok.*;

import javax.persistence.*;
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@ToString
@Table(name = "music_table")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 지정
    private Long id;
    @Column
    private String number;
    @Column
    private String image;
    @Column
    private String title;

    public Music(String number, String image ,String title) {
        this.number = number;
        this.image = image;
        this.title = title;
    }
    public static Music toMusic(MusicDTO musicDTO) {
        Music music = new Music();
        music.setNumber(musicDTO.getNumber());
        music.setImage(musicDTO.getImage());
        music.setTitle(musicDTO.getTitle());
        return music;
    }
}
