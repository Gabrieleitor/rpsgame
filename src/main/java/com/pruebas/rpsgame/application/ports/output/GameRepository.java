package com.pruebas.rpsgame.application.ports.output;

import com.pruebas.rpsgame.domain.Game;

import java.util.Optional;

public interface GameRepository {
    Game save(Game game);

    Optional<Game> findById(Long id);
}
