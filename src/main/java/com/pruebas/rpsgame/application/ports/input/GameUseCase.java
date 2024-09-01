package com.pruebas.rpsgame.application.ports.input;

import com.pruebas.rpsgame.domain.Game;
import com.pruebas.rpsgame.domain.Move;

public interface GameUseCase {
    Game createGame(String name);

    Game makeMove(Long gameId, Move move);

    Game getGame(Long gameId);
}
