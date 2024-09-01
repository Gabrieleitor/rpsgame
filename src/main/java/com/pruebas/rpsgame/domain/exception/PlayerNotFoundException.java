package com.pruebas.rpsgame.domain.exception;


public class PlayerNotFoundException extends Exception {
    public PlayerNotFoundException(Long id) {
        super("Player not found with id: " + id);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
