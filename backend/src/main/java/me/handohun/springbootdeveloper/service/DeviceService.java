package me.handohun.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.handohun.springbootdeveloper.domain.Company;
import me.handohun.springbootdeveloper.domain.Device;
import me.handohun.springbootdeveloper.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public List<Device> findAll() { // 테이블에 저장되어있는 모든 데이터 조회
        return deviceRepository.findAll();
    }
    public Device findById(Long id) {
        return deviceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not device found : " + id));
    }
}
