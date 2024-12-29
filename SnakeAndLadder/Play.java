package SnakeAndLadder;

import java.util.*;

public class Play {

    List<List<Integer>> snakes = new ArrayList<>();
    List<List<Integer>> ladders = new ArrayList<>();
    private snakeAndLadderBoard board;
    HashMap<Integer, Player> playerLookUp = new HashMap<>();
    HashSet<Integer> uniqueIds = new HashSet<>();
    List<Player> playersList = new ArrayList<>();
    GameControls getGameControls;
    Queue<Player> trunQueue = new LinkedList<>();
    Random random = new Random();

    private void initializeSnakesAndLadders() {
        snakes.add(new ArrayList<>(List.of(98, 78)));
        snakes.add(new ArrayList<>(List.of(64, 46)));
        snakes.add(new ArrayList<>(List.of(32, 10)));
        ladders.add(new ArrayList<>(List.of(3, 22)));
        ladders.add(new ArrayList<>(List.of(15, 47)));
        ladders.add(new ArrayList<>(List.of(60, 95)));
    }

    public void getAllPlayers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter all player names separated by commas:");
        String playersInput = scanner.nextLine();
        String[] playerNames = playersInput.split(",");
        int next_playerID = 1;
        // reads all the string and creates all the players for the game
        for (String playerName : playerNames) {
            Player currPlayer = new Player(playerName, next_playerID);
            playersList.add(currPlayer);
            playerLookUp.put(next_playerID, currPlayer);
            trunQueue.add(currPlayer);
            next_playerID++;
        }
    }

    public void playGame() {
        board = snakeAndLadderBoard.getInstance(snakes, ladders);
        getGameControls = new GameControls();
        getGameControls.startGame(playersList, board);
        while (getGameControls.getStatusOfCurentGame() == gameStatus.IN_PROGRESS) {
            Player currPlayer = trunQueue.poll();
            int diceRoll = random.nextInt(6) + 1;
            getGameControls.makeMove(currPlayer.getplayerID(), diceRoll);
            trunQueue.add(currPlayer);
        }
        System.out.println("Game has ended!!!");
    }

    public static void main(String[] args) {
        Play game = new Play();
        game.initializeSnakesAndLadders();
        game.getAllPlayers();
        game.playGame();
    }
}
