package com.pruebas.rpsgame.application.service;

import com.pruebas.rpsgame.application.ports.output.GameRepository;
import com.pruebas.rpsgame.application.ports.output.PlayerRepository;
import com.pruebas.rpsgame.domain.Game;
import com.pruebas.rpsgame.domain.Move;
import com.pruebas.rpsgame.domain.Player;
import com.pruebas.rpsgame.domain.exception.GameNotFoundException;
import com.pruebas.rpsgame.domain.exception.PlayerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private PlayerRepository playerRepository;

    private GameService gameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gameService = new GameService(gameRepository, playerRepository);
    }

    @Test
    void createGame_shouldCreateNewGame() {
        Player player = new Player( "TestPlayer");
        when(playerRepository.findByName("TestPlayer")).thenReturn(Optional.of(player));
        when(gameRepository.save(any(Game.class))).thenAnswer(i -> i.getArguments()[0]);

        Game game = gameService.createGame("TestPlayer");

        assertNotNull(game);
        assertEquals(player, game.getPlayer());
        verify(gameRepository).save(any(Game.class));
    }

    @Test
    void makeMove_shouldUpdateGameAndPlayerScore() {
        Player player = new Player( "TestPlayer");
        Game game = new Game(1L, player);
        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));
        when(gameRepository.save(any(Game.class))).thenAnswer(i -> i.getArguments()[0]);
        when(playerRepository.save(any(Player.class))).thenAnswer(i -> i.getArguments()[0]);

        Game updatedGame = gameService.makeMove(1L, Move.ROCK);

        assertNotNull(updatedGame);
        assertEquals(1, updatedGame.getPlayerMoves().size());
        assertEquals(1, updatedGame.getComputerMoves().size());
        verify(gameRepository).save(any(Game.class));
    }

    @Test
    void makeMove_shouldThrowGameNotFoundException() {
        when(gameRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(GameNotFoundException.class, () -> gameService.makeMove(1L, Move.ROCK));
    }
}