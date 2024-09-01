package com.pruebas.rpsgame.application.service;

import com.pruebas.rpsgame.application.ports.input.LeaderboardUseCase;
import com.pruebas.rpsgame.application.ports.output.PlayerRepository;
import com.pruebas.rpsgame.domain.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardService implements LeaderboardUseCase {
    private final PlayerRepository playerRepository;

    public LeaderboardService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> getTopPlayers(int limit) {
        return playerRepository.findTopPlayersByScore(limit);
    }
}
