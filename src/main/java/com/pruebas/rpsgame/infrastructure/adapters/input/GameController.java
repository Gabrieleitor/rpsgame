package com.pruebas.rpsgame.infrastructure.adapters.input;

import com.pruebas.rpsgame.application.ports.input.GameUseCase;
import com.pruebas.rpsgame.domain.Game;
import com.pruebas.rpsgame.domain.Move;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameUseCase gameUseCase;

    public GameController(GameUseCase gameUseCase) {
        this.gameUseCase = gameUseCase;
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestParam String playerName) {
        Game game = gameUseCase.createGame(playerName);
        return ResponseEntity.ok(game);
    }

    @PostMapping("/{gameId}/moves")
    public ResponseEntity<Game> makeMove(@PathVariable Long gameId, @RequestParam Move move) {
        Game game = gameUseCase.makeMove(gameId, move);
        return ResponseEntity.ok(game);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGame(@PathVariable Long gameId) {
        Game game = gameUseCase.getGame(gameId);
        return ResponseEntity.ok(game);
    }
}
