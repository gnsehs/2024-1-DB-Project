package me.handohun.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Table(name = "company")
@Entity // 엔티티
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 1 증가
    @Column(name = "company_id", updatable = false)
    private Long id;

    @Column(name = "name",updatable = false,nullable = false)
    private String name;

    @Column(name = "nation",updatable = false,nullable = false)
    private String nation;

    @Column(name = "founding_date",updatable = false,nullable = false)
    private LocalDate founding_date;

    @Column(name = "ceo",updatable = false,nullable = false)
    private String ceo;

    @OneToMany(mappedBy = "company")
    private List<Game> games;
}
