package TikTakToe;

import java.util.*;

// make a move for player, check if there is a win, if there is a win end the game by printing the winner
// ability to start game/ end game/ make move
// any game has startGame, endGame, makeMove, getGameStatus and all other helper functions

public class GameControls {
    private GameStatus status = GameStatus.NOT_STARTED;
    HashMap<Integer, Player> playerMap = new HashMap<>();
    HashMap<Integer, Character> playerPiece = new HashMap<>();

    private TikTakToeBoard board;

    public GameStatus getGameStatus() {
        return this.status;
    }

    public void startGame(List<Player> players, int n) {
        if (status != GameStatus.NOT_STARTED) {
            System.out.println("Game is already started or just ended. Please start new Game!!");
        }
        // keeping track of all the players for the game
        for (Player p : players) {
            playerMap.put(p.playerID(), p);
            playerPiece.put(p.playerID(), p.getGamepiece());
        }
        this.board = new TikTakToeBoard(n);
        this.status = GameStatus.IN_PROGRESS;
        System.out.println("Game started with players: " + players);
    }

    public void endGame() {
        if (status != GameStatus.IN_PROGRESS) {
            throw new IllegalStateException("Cannot end the game which is not strated!")
        }
        playerMap.clear();
        playerPiece.clear();
        status = GameStatus.COMPLETED;
    }

    public void makeMove(int id, int[] nextMove) {
        char currPlayerPiece = playerPiece.get(id);
        int boardDimension = board.getDimension();
        if (!isValid(boardDimension, boardDimension, nextMove[0], nextMove[1])) {
            throw new ArrayIndexOutOfBoundsException("The move is out of range for the given board");
        }
        board.setPlaceValue(nextMove, currPlayerPiece);
        if (checkIfWinner(currPlayerPiece, nextMove)) {
            System.out.println("Found the winner!!!");
            System.out.println("Winner is " + playerMap.get(id).getPlayerName());
            endGame();
        }
    }

    // If we are making a move, winner can happen only at the current move
    // checking horizontally, vertically, diagonally for the current move
    public boolean checkIfWinner(char piece, int[] nextMove) {
        boolean isWinner = false;
        boolean rowMatch = true, colMatch = true, rightDiagonal = true, leftDiagonal = true;
        int row = nextMove[0], col = nextMove[1];
        int boardDimension = board.getDimension();
        for (int i = 0; i < boardDimension; i++) {
            if (board.getPlaceValue(row, i) != piece) {
                rowMatch = false;
            }
            if (board.getPlaceValue(i, col) != piece) {
                colMatch = false;
            }
        }
        isWinner = rowMatch || colMatch;
        if (row == col || (row + col) == boardDimension) {
            for (int i = 0; i < boardDimension; i++) {
                if (board.getPlaceValue(i, i) != piece) {
                    leftDiagonal = false;
                }
            }
            isWinner = isWinner || leftDiagonal;
        }
        if ((row + col) == boardDimension) {
            for (int i = 0; i < boardDimension; i++) {
                if (board.getPlaceValue(i, (boardDimension - i - 1)) != piece) {
                    rightDiagonal = false;
                }
            }
            isWinner = isWinner || rightDiagonal;
        }
        return isWinner;

    }

    public boolean isValid(int m, int n, int x, int y) {
        return (x >= 0 && x < m && y >= 0 && y < n);
    }
}
