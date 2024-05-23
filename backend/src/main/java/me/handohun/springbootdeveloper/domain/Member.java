package me.handohun.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // 엔티티
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자를 별도 코드 없이 생성
public class Member {
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 1 증가
    @Column(name = "member_id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false) // not null
    private String name;


}
