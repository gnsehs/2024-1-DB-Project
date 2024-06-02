package me.handohun.springbootdeveloper.repository;

import me.handohun.springbootdeveloper.domain.GameOnDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameOnDeviceRepository extends JpaRepository<GameOnDevice, Long> {

}
