package com.pruebas.rpsgame.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {
    private Long id;
    private String name;
    private int highScore;
    private int tie;

    public Player(String name) {
        this.id =0L;
        this.name = name;
        this.highScore = 0;
        this.tie = 0;
    }

    public void updateHighScore(int score) {
        if (score > this.highScore) {
            this.highScore = score;
        }
    }

    public void updateTie() {
        this.tie++;
    }
}
