package com.pruebas.rpsgame.infrastructure.adapters.input;

import com.pruebas.rpsgame.application.ports.input.LeaderboardUseCase;
import com.pruebas.rpsgame.domain.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {
    private final LeaderboardUseCase leaderboardUseCase;

    public LeaderboardController(LeaderboardUseCase leaderboardUseCase) {
        this.leaderboardUseCase = leaderboardUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Player>> getTopPlayers(@RequestParam(defaultValue = "10") int limit) {
        List<Player> topPlayers = leaderboardUseCase.getTopPlayers(limit);
        return ResponseEntity.ok(topPlayers);
    }
}
