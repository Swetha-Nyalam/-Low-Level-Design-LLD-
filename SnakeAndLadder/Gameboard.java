package SnakeAndLadder;

import java.util.*;

class snakeAndLadderBoard {
    private static snakeAndLadderBoard game;
    HashMap<Integer, Integer> snakes = new HashMap<>();
    HashMap<Integer, Integer> ladders = new HashMap<>();

    private snakeAndLadderBoard(List<List<Integer>> snakesList, List<List<Integer>> laddersList) {
        for (List<Integer> snake : snakesList) {
            this.snakes.put(snake.get(0), snake.get(1));
        }
        for (List<Integer> ladder : laddersList) {
            this.ladders.put(ladder.get(0), ladder.get(1));
        }
    }

    public static snakeAndLadderBoard getInstance(List<List<Integer>> snakesList, List<List<Integer>> laddersList) {
        if (game == null) {
            game = new snakeAndLadderBoard(snakesList, laddersList);
        }
        return game;
    }
}
