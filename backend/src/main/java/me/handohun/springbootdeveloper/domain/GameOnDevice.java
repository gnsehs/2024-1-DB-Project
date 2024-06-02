package me.handohun.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Table(name = "game_on_device")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GameOnDevice {
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 1 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

}
