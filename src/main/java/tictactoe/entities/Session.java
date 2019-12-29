package tictactoe.entities;

public class Session {
    private Player playerOne;
    private Player playerTwo;
    private Board board;
    private int id;

    public Session(Player playerOne, Player playerTwo, Board board, int sessionId) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.board = board;
        this.id = sessionId;
    }

    public void StartGame() {
        // do something
    }
}
