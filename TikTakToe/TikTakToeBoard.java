package TikTakToe;

public class TikTakToeBoard {
    private char[][] board;

    public TikTakToeBoard(int n) {
        board = new char[n][n];
    }

    public void setPlaceValue(int[] move, char playerPiece) {
        board[move[0]][move[1]] = playerPiece;
    }

    public char getPlaceValue(int x, int y) {
        return board[x][y];
    }

    public int getDimension() {
        return board.length;
    }

}
