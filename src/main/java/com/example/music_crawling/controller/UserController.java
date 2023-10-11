package com.example.music_crawling.controller;

import com.example.music_crawling.dto.UserDTO;
import com.example.music_crawling.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UserController {
    // 생성자 주입
    private final UserService userService;

    @GetMapping("/signup")
    public String Signup() {
        return "signup";
    }
    @PostMapping("/signup")   // 회원가입 구현
    public String Signup(@ModelAttribute UserDTO userDTO) {
        userService.save(userDTO);
        return "login";
    }

    @PostMapping("/login")   // 로그인 구현
    public String Login(@ModelAttribute UserDTO userDTO, HttpSession session) {
        UserDTO loginResult = userService.login(userDTO);
        if(loginResult != null) {
            if(userDTO.getUserPw().equals(loginResult.getUserPw())) {
                session.setAttribute("loginEmail", loginResult.getUserEmail());
                System.out.println("================ 로그인 성공: " + session.getAttribute("loginEmail") + " ================");
                return "redirect:/main";
            }
            else {
                System.out.println("================ 비밀번호 불일치 ================");
                return "redirect:/";
            }
        }
        else {
            System.out.println("================ 이메일 불일치 ================");
            return "redirect:/";
        }
    }
    @GetMapping("/find_user")
    public String Find_User() {
        return "find_user";
    }
    @GetMapping("/find_pw")
    public String Find_Pw() {
        return "signup";
    }

    @PostMapping("/update")
    public String Update(UserDTO userDTO, HttpSession session) {
        String loginEmail = (String)session.getAttribute("loginEmail");
        userService.update(userDTO, loginEmail);
        return "redirect:/main";
    }
    @GetMapping("/update")
    public String GetUser(HttpSession session, Model model) {
        String loginEmail = (String)session.getAttribute("loginEmail");
        UserDTO loginUser = userService.getUser(loginEmail);
        // 모델에 데이터 추가
        model.addAttribute("userEmail", loginUser.getUserEmail());
        model.addAttribute("userPhone", loginUser.getUserPhone());
        model.addAttribute("userPw", loginUser.getUserPw());

        return "/update";
    }

    @PostMapping("/delete") // 회원탈퇴 구현
    public String Delete(HttpSession session) {
        String loginEmail = (String)session.getAttribute("loginEmail");
        userService.delete(loginEmail);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String Logout(HttpSession session) {
        System.out.println("================ 현재 세션: " + session.getAttribute("loginEmail") + " ================");
        session.invalidate();
        return "/login";
    }

    @PostMapping("/find_user")
    public String Find_User(@RequestParam("userPhone") String userPhone, Model model) {
        UserDTO user = userService.find_User(userPhone);
        if (user != null) {
            // UserDTO 객체를 Model에 추가
            model.addAttribute("user", user);
        }
        return "find_result";
    }

    @PostMapping("/find_pw")
    public String Find_Pw(@RequestParam("userEmail") String userPhone, Model model) {
        UserDTO user = userService.find_User(userPhone);
        if (user != null) {
            // UserDTO 객체를 Model에 추가
            model.addAttribute("user", user);
        }
        return "find_result";
    }

}
