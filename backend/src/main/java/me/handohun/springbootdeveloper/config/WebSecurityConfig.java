package me.handohun.springbootdeveloper.config;

import lombok.RequiredArgsConstructor;
import me.handohun.springbootdeveloper.config.jwt.TokenAuthenticationFilter;
import me.handohun.springbootdeveloper.config.jwt.TokenProvider;
import me.handohun.springbootdeveloper.service.UserDetailService;
import me.handohun.springbootdeveloper.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailService userService;
    private final TokenProvider tokenProvider;

    // 1. 스프링 시큐리티 기능 비활성화
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console()) // h2-console 하위 url
                .requestMatchers(new AntPathRequestMatcher("/static/**"));
    }

    // 2. 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth // 3. 인증 인가 설정: 특정 경로에 대한 엑세스 설정
                        .requestMatchers(
                                new AntPathRequestMatcher("/login"), // 특정 요청과 일치하는 url에 대한 엑세스 설정
                                new AntPathRequestMatcher("/signup")
                        ).permitAll() // 누구나 접근 가능하게, 즉 /login등으로 요청이 오면 인증 인가 없이도 접근 가능
                                .anyRequest().authenticated()) // 위에서 설정한 url외에는 인증이 성공된 상태여야 접근 가능
                .addFilterBefore(new TokenAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout // 5. 로그아웃 설정
                        .logoutSuccessUrl("/login") // 로그아웃 완료 되었을시 이동
                        .invalidateHttpSession(true) // 로그아웃 이후에 세션 전체 삭제
                )
                .csrf(AbstractHttpConfigurer::disable) // 더블 콜론 연산자 6. csrf  비활성화
                .build();

    }

    // 7. 인증 관리자 관련 설정 -> 사용자 정보 가져올 서비스 재정의, 인증 방법,
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder
    , UserService userDetailService) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService); // 8. 사용자 정보 서비스 설정 // UserDetailsService 구현 해야함
        authProvider.setPasswordEncoder(bCryptPasswordEncoder); // 비밀번호 암호화 위한 인코더
        return new ProviderManager(authProvider);

    }
// branch test
    // 9. 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }







}
