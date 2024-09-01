    package com.pruebas.rpsgame.application.service;

import com.pruebas.rpsgame.application.ports.input.GameUseCase;
import com.pruebas.rpsgame.application.ports.output.GameRepository;
import com.pruebas.rpsgame.application.ports.output.PlayerRepository;
import com.pruebas.rpsgame.domain.Game;
import com.pruebas.rpsgame.domain.Move;
import com.pruebas.rpsgame.domain.Player;
import com.pruebas.rpsgame.domain.exception.GameAlreadyFinishedException;
import com.pruebas.rpsgame.domain.exception.GameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameService implements GameUseCase {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final Random random = new Random();

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public Game createGame(String name) {
        Player player = playerRepository.findByName(name).orElse(new Player(name));
        Game game = new Game(null, player);
        return gameRepository.save(game);
    }

    @Override
    public Game makeMove(Long gameId, Move playerMove) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException(gameId));

        if (game.isFinished()) {
            throw new GameAlreadyFinishedException(gameId);
        }

        Move computerMove = getRandomMove();
        game.addMove(playerMove, computerMove);

        if (isPlayerWinner(playerMove, computerMove)) {
            game.getPlayer().updateHighScore(game.getPlayerMoves().size());
            playerRepository.save(game.getPlayer());
        } else if (playerMove.equals(computerMove)) {
            game.getPlayer().updateTie();
        } else {
            game.finish();
        }

        return gameRepository.save(game);
    }

    @Override
    public Game getGame(Long gameId) {
        return gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));
    }

    private Move getRandomMove() {
        return Move.values()[random.nextInt(Move.values().length)];
    }

    private boolean isPlayerWinner(Move playerMove, Move computerMove) {
        return (playerMove == Move.ROCK && computerMove == Move.SCISSORS) ||
                (playerMove == Move.PAPER && computerMove == Move.ROCK) ||
                (playerMove == Move.SCISSORS && computerMove == Move.PAPER);
    }
}
