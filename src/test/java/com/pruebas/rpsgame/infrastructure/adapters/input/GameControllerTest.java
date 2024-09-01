package com.pruebas.rpsgame.infrastructure.adapters.input;



import com.pruebas.rpsgame.application.ports.input.GameUseCase;
import com.pruebas.rpsgame.domain.Game;
import com.pruebas.rpsgame.domain.Move;
import com.pruebas.rpsgame.domain.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GameController.class)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameUseCase gameUseCase;

    @Test
    void createGame_shouldReturnCreatedGame() throws Exception {
        Player player = new Player(1L, "TestPlayer", 0,0);
        Game game = new Game(1L, player);
        when(gameUseCase.createGame("TestPlayer")).thenReturn(game);

        mockMvc.perform(post("/games?playerName=TestPlayer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.player.id").value(1))
                .andExpect(jsonPath("$.player.name").value("TestPlayer"));
    }

    @Test
    void makeMove_shouldReturnUpdatedGame() throws Exception {
        Player player = new Player(1L, "TestPlayer", 0,0);
        Game game = new Game(1L, player);
        game.addMove(Move.ROCK, Move.SCISSORS);
        when(gameUseCase.makeMove(1L, Move.ROCK)).thenReturn(game);

        mockMvc.perform(post("/games/1/moves?move=ROCK")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.playerMoves[0]").value("ROCK"))
                .andExpect(jsonPath("$.computerMoves[0]").value("SCISSORS"));
    }

    @Test
    void getGame_shouldReturnGame() throws Exception {
        Player player = new Player(1L, "TestPlayer", 0,0);
        Game game = new Game(1L, player);
        when(gameUseCase.getGame(1L)).thenReturn(game);

        mockMvc.perform(get("/games/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.player.id").value(1))
                .andExpect(jsonPath("$.player.name").value("TestPlayer"));
    }
}