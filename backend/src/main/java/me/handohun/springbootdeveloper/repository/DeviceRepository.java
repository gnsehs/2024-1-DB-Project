package me.handohun.springbootdeveloper.repository;

import me.handohun.springbootdeveloper.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
