package TikTakToe;

public class Player {
    private String player;
    private int playerID;
    private char gamepiece;

    Player(String player, int playerID) {
        this.player = player;
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return player;
    }

    public char getGamepiece() {
        return this.gamepiece;
    }

    public int playerID() {
        return playerID;
    }

}
