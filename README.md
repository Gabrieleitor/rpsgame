# Juego de Piedra Papel o Tijera

## Descripción 
Se juega de un jugador por partida, se debe crear una partida y esta finaliza cuando el jugador pierde.

### Pre-requisitos 📋

se necesita 
1 java 17 
2 Gradle

## Request

Para crear el Juego se requiere el nombre del jugador

```
POST http://localhost:8080/games?playerName=marcos
```
Para jugar se debe enviar el nombre de la partida y la opción con que se va ha jugar

```
POST http://localhost:8080/games/1/moves?move=SCISSORS
```

Después de que pasen todos los participantes se puede visualizar quien es el ganador.

```
GET http://localhost:8080/leaderboard
```
