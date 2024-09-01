package com.pruebas.rpsgame.application.ports.input;

import com.pruebas.rpsgame.domain.Player;

import java.util.List;

public interface LeaderboardUseCase {
    List<Player> getTopPlayers(int limit);
}
