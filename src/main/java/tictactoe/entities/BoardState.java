package tictactoe.entities;

import tictactoe.entities.Player;

public class BoardState {
    private final String[][] board;
    private final Player playerOne;
    private final Player playerTwo;

    public BoardState(String[][] board, Player playerOne, Player playerTwo) {
        this.board = board;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public String[][] getBoard() {
        return board;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }
}
