package com.example.music_crawling.dto;

import com.example.music_crawling.entitiy.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private Long id;
    private String userEmail;
    private String userPw;
    private String userPhone;

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserEmail(user.getUserEmail());
        userDTO.setUserPw(user.getUserPw());
        userDTO.setUserPhone(user.getUserPhone());
        return userDTO;
    }
}
