package com.pruebas.rpsgame.application.ports.output;

import com.pruebas.rpsgame.domain.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {
    Player save(Player player);

    Optional<Player> findByName(String name);

    List<Player> findTopPlayersByScore(int limit);
}
