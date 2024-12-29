package SnakeAndLadder;

public class Player {
    private String playerName;
    private int playerID;

    public Player(String name, int ID) {
        playerName = name;
        playerID = ID;
    }

    public String getplayerName() {
        return playerName;
    }

    public int getplayerID() {
        return playerID;
    }
}
