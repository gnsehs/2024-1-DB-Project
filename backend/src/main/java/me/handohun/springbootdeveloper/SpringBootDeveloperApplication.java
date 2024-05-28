package me.handohun.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class) // 메인 애플리케이션 클래스에 추가하는 애너테이션
public class SpringBootDeveloperApplication { // 자바의 main() 같은 역할 즉 여기서 스프링 부트 시작
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }
}
