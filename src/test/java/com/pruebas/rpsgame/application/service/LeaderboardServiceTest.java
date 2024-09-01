package com.pruebas.rpsgame.application.service;

import com.pruebas.rpsgame.application.ports.output.PlayerRepository;
import com.pruebas.rpsgame.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LeaderboardServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    private LeaderboardService leaderboardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        leaderboardService = new LeaderboardService(playerRepository);
    }

    @Test
    void getTopPlayers_shouldReturnSortedList() {
        List<Player> players = Arrays.asList(
                new Player( "Player1")
        );
        when(playerRepository.findTopPlayersByScore(3)).thenReturn(players);

        List<Player> topPlayers = leaderboardService.getTopPlayers(3);

        assertEquals(1, topPlayers.size());
        assertEquals(0, topPlayers.get(0).getHighScore());

    }
}