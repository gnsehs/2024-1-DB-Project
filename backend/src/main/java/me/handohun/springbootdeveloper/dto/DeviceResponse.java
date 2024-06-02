package me.handohun.springbootdeveloper.dto;


import lombok.Getter;
import me.handohun.springbootdeveloper.domain.Device;
import me.handohun.springbootdeveloper.domain.GameOnDevice;

import java.time.LocalDate;

@Getter
public class DeviceResponse {
    final private String device_name;
    final private String made_by;
    final private LocalDate release_date;
    final private int num_game;
    final private int exclusive_game_num;

    public DeviceResponse(Device device) {
        this.device_name = device.getDevice_name();
        this.made_by = device.getMade_by();
        this.release_date = device.getRelease_date();
        this.num_game = device.getGameOnDevices().size(); // game_counting
        this.exclusive_game_num = (int) device.getGameOnDevices().stream() // only_game_counting
                .filter(gameOnDevice -> isExclusive(gameOnDevice))
                .count();
    }


    private boolean isExclusive(GameOnDevice gameOnDevice) {
        return gameOnDevice.getGame().getGameOnDevices().size() == 1;
    }

}
