package SnakeAndLadder;

import java.util.*;

// startGame, endGame, makeMove, checkWinner,
// ifWinner{end Game;}

public class GameControls {

    private gameStatus status = gameStatus.NOT_STARTED;
    HashMap<Integer, Player> playerMap = new HashMap<>();
    HashMap<Integer, Integer> currPosition = new HashMap<>();
    private int gameEndsAt = 100;
    private snakeAndLadderBoard board;

    public void startGame(List<Player> players, snakeAndLadderBoard board) {
        if (status != gameStatus.NOT_STARTED) {
            throw new IllegalStateException("Cannot start the game. Current status: " + status);
        }
        for (Player nextPlayer : players) {
            playerMap.put(nextPlayer.getplayerID(), nextPlayer);
            currPosition.put(nextPlayer.getplayerID(), 0);
        }
        this.board = board;
        status = gameStatus.IN_PROGRESS;
        System.out.println("Game started with players: " + players);
    }

    public void endGame() {
        if (status != gameStatus.IN_PROGRESS) {
            throw new IllegalStateException("Cannot end a game that is not in progress.");
        }
        status = gameStatus.COMPLETED;
        System.out.println("Game ended.");
    }

    public gameStatus getStatusOfCurentGame() {
        return status;
    }

    public void makeMove(Integer playerID, Integer moveMagnitude) {
        Player currPlayer = playerMap.get(playerID);
        int currMove = currPosition.get(currPlayer.getplayerID()) + moveMagnitude;
        // Not making any move if the current move is invalid
        if (currMove > gameEndsAt) {
            System.out.println("Could not make this move this time!");
            return;
        }
        if (currMove == gameEndsAt) {
            System.out.println("We have the Winner, Winner is : " + currPlayer.getplayerName());
            playerMap.clear();
            currPosition.clear();
            endGame();
            return;
        }
        // checking for snakes or ladder in the current move or else updating
        int nextStep = 0;
        if (board.snakes.containsKey(currMove)) {
            // System.out.println("ohh no! Bite from a Snake for : " +
            // currPlayer.getplayerName());
            nextStep = board.snakes.get(currMove);
        } else if (board.ladders.containsKey(currMove)) {
            // System.out.println("ohh yes! Climb the ladder now for : " +
            // currPlayer.getplayerName());
            nextStep = board.ladders.get(currMove);
        } else {
            nextStep = currMove;
        }
        System.out.println("Moving " + currPlayer.getplayerName() + " from "
                + currPosition.get(currPlayer.getplayerID()) + " to " + nextStep + " moveMagnitude is "
                + moveMagnitude);
        currPosition.put(currPlayer.getplayerID(), nextStep);
    }
}