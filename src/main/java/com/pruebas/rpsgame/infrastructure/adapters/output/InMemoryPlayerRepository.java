package com.pruebas.rpsgame.infrastructure.adapters.output;

import com.pruebas.rpsgame.application.ports.output.PlayerRepository;
import com.pruebas.rpsgame.domain.Player;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class InMemoryPlayerRepository implements PlayerRepository {
    private final Map<Long, Player> players = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public Player save(Player player) {
        if (player.getId() == 0L) {
            player.setId(idCounter.incrementAndGet());
        }
        players.put(player.getId(), player);
        return player;
    }

    @Override
    public Optional<Player> findByName(String name) {
        return Optional.ofNullable(players.get(name));
    }

    @Override
    public List<Player> findTopPlayersByScore(int limit) {
        return players.values().stream()
                .sorted(Comparator.comparingInt(Player::getHighScore).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }
}
