package com.example.music_crawling.service;

import com.example.music_crawling.dto.UserDTO;
import com.example.music_crawling.entitiy.User;
import com.example.music_crawling.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public Boolean save(UserDTO userDTO) {
        User user = User.toUser(userDTO);
        Optional<User> byUserEmail = userRepository.findByUserEmail(user.getUserEmail());
        if(!byUserEmail.isPresent())
        {
            userRepository.save(user);
            System.out.println(user.getUserEmail()+"님이 저장되었습니다.");
            return true;
        }
        else {
            System.out.println(user.getUserEmail()+"님이 이미 존재합니다.");
            return false;
        }
    }

    public UserDTO login(UserDTO userDTO) {
        Optional<User> byUserEmail = userRepository.findByUserEmail(userDTO.getUserEmail());
        if(byUserEmail.isPresent()) {
            User user = byUserEmail.get();
            UserDTO dto = UserDTO.toUserDTO(user);
            return dto;
        }
        else{
            System.out.println("================ 로그인: 존재하지 않습니다. ================");
            return null;
        }
    }
    public void update(UserDTO userDTO, String loginEmail) {
        Optional<User> optionalUser = userRepository.findByUserEmail(loginEmail);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            User updatedUser = User.toUpdateUser(userDTO, user); // 사용자 정보 업데이트
            userRepository.save(updatedUser);
        } else {
            System.out.println(loginEmail + "님이 수정실패되었습니다.");
        }
    }
    public UserDTO getUser(String loginEmail) {
        Optional<User> optionalUser = userRepository.findByUserEmail(loginEmail);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDTO userDTO = UserDTO.toUserDTO(user);
            return userDTO;
        }
        else {
            return null;
        }
    }

    public void delete(String userEmail) {
        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Long myId = user.getId();

            // 사용자 정보 삭제
            userRepository.deleteById(myId);
        } else {
            System.out.println("존재 안한다");
        }
    }

    public UserDTO find_User(String userPhone) {
        Optional<User> optionalUserPhone = userRepository.findByUserPhone(userPhone);
        if (optionalUserPhone.isPresent()) {
            User user = optionalUserPhone.get();
            UserDTO userDTO = UserDTO.toUserDTO(user);
            return userDTO;

        } else {
            System.out.println("존재 안한다");
            return null;
        }
    }
}
