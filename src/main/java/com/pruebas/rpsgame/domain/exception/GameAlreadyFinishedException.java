package com.pruebas.rpsgame.domain.exception;

public class GameAlreadyFinishedException extends RuntimeException {
    public GameAlreadyFinishedException(Long id) {
        super("Game with id: " + id + " is already finished");
    }
}
