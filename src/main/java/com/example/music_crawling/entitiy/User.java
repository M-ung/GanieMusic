package com.example.music_crawling.entitiy;

import com.example.music_crawling.dto.UserDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 지정
    private Long id;

    @Column
    private String userEmail;

    @Column
    private String userPw;

    @Column
    private String userPhone;


    public static User toUser(UserDTO userDTO) {
        User user = new User();
        user.setUserEmail(userDTO.getUserEmail());
        user.setUserPw(userDTO.getUserPw());
        user.setUserPhone(userDTO.getUserPhone());
        return user;
    }

    public static User toUpdateUser(UserDTO userDTO, User existingUser) {
        existingUser.setUserEmail(userDTO.getUserEmail());
        existingUser.setUserPhone(userDTO.getUserPhone());
        existingUser.setUserPw(userDTO.getUserPw());
        return existingUser;
    }

}
