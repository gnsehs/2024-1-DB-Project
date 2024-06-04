package me.handohun.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.handohun.springbootdeveloper.domain.User;
import me.handohun.springbootdeveloper.dto.AddUserRequest;
import me.handohun.springbootdeveloper.service.UserService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Long> signup(@RequestBody AddUserRequest request) {
        Long id = userService.save(request);
        return ResponseEntity.ok().body(id);  // 회원 가입이 완료된 이후에 로그인 페이지로 이동

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AddUserRequest request) {
        String tempToken = userService.login(request);
        return ResponseEntity.ok().body(tempToken);
    }
}

