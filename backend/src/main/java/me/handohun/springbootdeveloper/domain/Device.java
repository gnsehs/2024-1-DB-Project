package me.handohun.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "device")
@Entity // 엔티티
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Device {
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 1 증가
    @Column(name = "device_id", updatable = false)
    private Long id;

    @Column(name = "device_name",updatable = false)
    private String device_name;

    @Column(name = "made_by",updatable = false)
    private String made_by;

    @Column(name = "release_date",updatable = false,nullable = false)
    private LocalDate release_date;

    @OneToMany(mappedBy = "device") // many to many
    private List<GameOnDevice> gameOnDevices = new ArrayList<>();

}
