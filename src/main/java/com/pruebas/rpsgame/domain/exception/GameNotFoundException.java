package com.pruebas.rpsgame.domain.exception;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(Long id) {
        super("Game not found with id: " + id);
    }
}
