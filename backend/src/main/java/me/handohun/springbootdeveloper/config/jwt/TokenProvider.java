package me.handohun.springbootdeveloper.config.jwt;
/*
토큰을 생성하고 올바른 토큰인지 유효성 검사, 토큰에서 필요한 정보를 가져오는 클래스 작성
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import me.handohun.springbootdeveloper.domain.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {
    private final JwtProperties jwtProperties;

    public String generateToken(User user, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()),user);
    }

    // 1. JWT 토큰 생성 메서드 -> 인자로 만료시간, 유저 정보를 받음
    private String makeToken(Date expiry, User user) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // 헤더타입 : JWT
                .setIssuer(jwtProperties.getIssuer()) // iss: 토큰 발급자 /* 여기서부터 내용 */
                .setIssuedAt(now) // 내용 iat 현재 시간 iat: 토큰이 발급 된 시간
                .setExpiration(expiry) // exp: expiry 멤버 변수값 => 토큰 만료 시간
                .setSubject(user.getUsername()) // 내용 sub: 토큰제목 => 유저의 이메일
                .claim("id",user.getId()) // id를 키값으로 유저의 아이디를 저장해놓음
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();

    }
    // 2. JWT 토큰 유효성 검사 메서드
    public boolean validToken(String token) {
        try {
            Jwts.parser() // 프로퍼티에 선언한 비밀값과 함께 토큰 복호화 진행
                    .setSigningKey(jwtProperties.getSecretKey()) // 비밀값으로 복호화
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false; // 복호화 과정에서 에러나면 유효하지 않은 토큰
        }
    }

    // 3. 토큰 기반으로 인증 정보를 가져오는 메서드
    public Authentication getAuthentication(String token) { // 인증 정보를 담은 객체를 반환
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        // 사용자 이메일이 들어있는 토큰 제목 sub와 토큰 기반으로 인증 정보를 생성
        return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(claims.getSubject
                (), "", authorities), token, authorities);
    } // 이때 User는 스프링 시큐리티 에서 제공하는 User CLASS

    public Long getUserId(String token) { // Token 기반으로 사용자 아이디를 가져옴
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
    }

    private Claims getClaims(String token) { // 토큰을 복호화 한뒤 claim을 가져옴
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();

    }

 }
