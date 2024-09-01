package com.pruebas.rpsgame.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Game {
    private Long id;
    private Player player;
    private List<Move> playerMoves;
    private List<Move> computerMoves;
    private boolean isFinished;

    public Game(Long id, Player player) {
        this.id = id;
        this.player = player;
        this.playerMoves = new ArrayList<>();
        this.computerMoves = new ArrayList<>();
        this.isFinished = false;
    }

    public void addMove(Move playerMove, Move computerMove) {
        playerMoves.add(playerMove);
        computerMoves.add(computerMove);
    }

    public void finish() {
        this.isFinished = true;
    }
}
