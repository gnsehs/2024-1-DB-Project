package me.handohun.springbootdeveloper.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import me.handohun.springbootdeveloper.domain.Game;

import java.time.LocalDate;

@Getter
public class GameResponse {
    final private Long game_id;
    final private String game_name;
    final private LocalDate release_date;
    final private String made_by;
    final private int age_rating;
    final private boolean exclusive;

    private final int num_device;

    // final private int num_device;

    public GameResponse(Game game) {
        this.game_id = game.getId();
        this.game_name = game.getGame_name();
        this.release_date = game.getRelease_date();
        this.made_by = game.getCompany().getName();
        this.age_rating = game.getAge_rating();
        if(game.getGameOnDevices().size() == 1) { // only
            this.exclusive = true;
        } else {
            this.exclusive = false;
        }
        this.num_device = game.getGameOnDevices().size(); // num device
        // this.num_device =
    }
}
