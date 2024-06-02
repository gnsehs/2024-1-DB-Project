package me.handohun.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "game")
@Entity // 엔티티
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Game {

    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 1 증가
    @Column(name = "game_id", updatable = false)
    private Long id;

    @Column(name = "game_name",updatable = false)
    private String game_name;

    @Column(name = "release_date",updatable = false)
    private LocalDate release_date;

    @ManyToOne
    @JoinColumn(name = "company_id") // made_by
    private Company company;

    @Column(name = "age_rating",updatable = false) // age
    private int age_rating;

//    @Column(name = "exclusive")
//    private Boolean exclusive;

    @OneToMany(mappedBy = "game") // many to many
    private List<GameOnDevice> gameOnDevices = new ArrayList<>();





}
