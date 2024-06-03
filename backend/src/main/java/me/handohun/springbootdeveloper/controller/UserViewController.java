package me.handohun.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.handohun.springbootdeveloper.dto.AddUserRequest;
import me.handohun.springbootdeveloper.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class UserViewController {
    private final UserService userService;

//    @GetMapping("/login")
//    public String login2() {
//        return "hello";
//    }
//
//    @GetMapping("/signup")
//    public String signup2() {
//        return "hello";
//    }

//    @PostMapping("/login") //
//    public String login(@RequestBody AddUserRequest request) {
//        return "redirect:/login"; // login.html
//    }

    @PostMapping("/signup")
    public String signup(@RequestBody AddUserRequest request) {
        userService.save(request); // 회원가입 메서드 호출
        return "redirect:/login"; // 회원 가입이 완료된 이후에 로그인 페이지로 이동

    }
}
