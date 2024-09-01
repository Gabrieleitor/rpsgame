package com.pruebas.rpsgame.infrastructure.adapters.output;

import com.pruebas.rpsgame.application.ports.output.GameRepository;
import com.pruebas.rpsgame.domain.Game;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryGameRepository implements GameRepository {
    private final Map<Long, Game> games = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public Game save(Game game) {
        if (game.getId() == null) {
            game.setId(idCounter.incrementAndGet());
        }
        games.put(game.getId(), game);
        return game;
    }

    @Override
    public Optional<Game> findById(Long id) {
        return Optional.ofNullable(games.get(id));
    }
}
