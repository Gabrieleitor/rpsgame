package com.pruebas.rpsgame.infrastructure.adapters.input;


import com.pruebas.rpsgame.application.ports.input.LeaderboardUseCase;
import com.pruebas.rpsgame.domain.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LeaderboardController.class)
class LeaderboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LeaderboardUseCase leaderboardUseCase;

    @Test
    void getTopPlayers_shouldReturnLeaderboard() throws Exception {
        List<Player> topPlayers = Arrays.asList(
                new Player(1L, "Player1", 200,0),
                new Player(2L, "Player2", 150,0),
                new Player(3L, "Player3", 100,0)
        );
        when(leaderboardUseCase.getTopPlayers(3)).thenReturn(topPlayers);

        mockMvc.perform(get("/leaderboard?limit=3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Player1"))
                .andExpect(jsonPath("$[0].highScore").value(200))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Player2"))
                .andExpect(jsonPath("$[1].highScore").value(150))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[2].name").value("Player3"))
                .andExpect(jsonPath("$[2].highScore").value(100));
    }
}