package me.handohun.springbootdeveloper.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.handohun.springbootdeveloper.dto.AddUserRequest;
import me.handohun.springbootdeveloper.service.UserService;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Controller
public class UserViewController {
    private final UserService userService;
    private final ObjectMapper objectMapper;

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
    public String signup(@RequestBody AddUserRequest request) throws JsonProcessingException {
        System.out.println("TEST:: "+objectMapper.writeValueAsString(request));
        userService.save(request); // 회원가입 메서드 호출
        return "redirect:/login"; // 회원 가입이 완료된 이후에 로그인 페이지로 이동
        /*
        redirect: 접두사 -> 회원 가입 처리가 끝나면 강제로 /login URL에 해당하는 화면으로 이동
         */
    }

    @PostMapping("/login")
    public String login(@RequestBody AddUserRequest request) {
        RestTemplate rt = new RestTemplate();
        String url = "http://localhost:8080/login";
        return rt.postForLocation(url,request).toString();
    }
    }

