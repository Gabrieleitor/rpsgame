package com.pruebas.rpsgame.infrastructure.config;

import com.pruebas.rpsgame.application.ports.input.GameUseCase;
import com.pruebas.rpsgame.application.ports.input.LeaderboardUseCase;
import com.pruebas.rpsgame.application.ports.output.GameRepository;
import com.pruebas.rpsgame.application.ports.output.PlayerRepository;
import com.pruebas.rpsgame.application.service.GameService;
import com.pruebas.rpsgame.application.service.LeaderboardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public GameUseCase gameUseCase(GameRepository gameRepository, PlayerRepository playerRepository) {
        return new GameService(gameRepository, playerRepository);
    }

    @Bean
    public LeaderboardUseCase leaderboardUseCase(PlayerRepository playerRepository) {
        return new LeaderboardService(playerRepository);
    }
}
