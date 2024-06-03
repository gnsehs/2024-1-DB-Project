package me.handohun.springbootdeveloper.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.handohun.springbootdeveloper.domain.User;
import me.handohun.springbootdeveloper.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;

//    @PostMapping("/user")
//    public String signup(@RequestBody AddUserRequest request) {
//        userService.save(request); // 회원가입 메서드 호출
//        return "redirect:/login"; // 회원 가입이 완료된 이후에 로그인 페이지로 이동
//    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request,response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

    @GetMapping("/username") // 현재 로그인한 user정보 가져오기
    public ResponseEntity<String> currentUserName(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(user.getUsername());
    }




}
