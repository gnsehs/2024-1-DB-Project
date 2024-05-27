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

    final private String game_name;
    final private LocalDate release_date;
    final private String made_by;
    final private int age_rating;
    final private boolean exclusive;

    // final private int num_device;

    public GameResponse(Game game) {
        this.game_name = game.getGame_name();
        this.release_date = game.getRelease_date();
        this.made_by = game.getCompany().getName();
        this.age_rating = game.getAge_rating();
        this.exclusive = game.getExclusive();
        // this.num_device =
    }
}
