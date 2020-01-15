package tictactoe.entities;


import java.util.Arrays;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;
import tictactoe.adapters.AlgorithmAdapter;

@Component
public class GameStateModel {

    @Id
    public String id;

    @CreatedDate
    private DateTime created;

    public String name;

    private Player playerOne;
    private Player playerTwo;

    //Ai
    AlgorithmAdapter algo = new AlgorithmAdapter();

    //For Mongo
    private String playerOneId;
    private String playerTwoId;

    public Board board = new Board(3,3);

    private String startingPlayer;
    private String currentPlayer;

    public String winner;

    public boolean started;
    public boolean disconnect;
    public boolean draw;

    public GameStateModel() {}

    public GameStateModel(String symbol, String name) {
        this.name = name;
        this.playerOne = new Player(symbol);
        this.playerTwo = null;
        this.startingPlayer = playerOne.getSymbol();
        this.currentPlayer = startingPlayer;
        this.winner = null;
        this.started = false;
        this.disconnect = false;
        this.draw = false;

        // initialize board
        resetBoard();
    }

    public String getStartingPlayer() {
        return startingPlayer.equals(playerOne.getSymbol()) ? "X" : "O";
    }

    public void join(String symbol) {
        if (this.playerTwo == null) {
            this.playerTwo = new Player(symbol);
            this.started = true;
        }
    }

    public void websocketJoin(String playerSymbol, String playerId) {
        if (playerSymbol.equals(playerOne.getSymbol())) {
            playerOne.setId(playerId);
            playerOneId = playerId;

        }
        else if (playerSymbol.equals(playerTwo.getSymbol())) {
            playerTwo.setId(playerId);
            playerTwoId = playerId;
        }
    }

    public void disconnect(String playerSymbol) {
        if (isValidPlayer(playerSymbol)) {
            disconnect = true;
        }
    }

    public void disconnectById(String playerId) {
        if (isValidPlayerId(playerId)) {
            disconnect = true;
        }
    }

    public void rematch(String playerSymbol) {
        if (!disconnect && gameOver() && isValidPlayer(playerSymbol)) {
            winner = null;
            draw = false;
            startingPlayer = startingPlayer.equals(playerOne.getSymbol()) ? playerTwo.getSymbol() : playerOne.getSymbol();
            currentPlayer = startingPlayer;

            resetBoard();
        }
    }

    public void makeMove(int x, int y, String playerSymbol) {

        // invalid move
        if (x < 0 || x >= 3) return;
        if (y < 0 || y >= 3) return;

        // invalid player
        if (!isValidPlayer(playerSymbol)) return;

        if (started && !disconnect && !gameOver() && currentPlayer.equals(playerSymbol) && board.tiles[x][y].equals("")) {

            String s = playerSymbol.equals(playerOne.getSymbol()) ? "X" : "O";
            board.setTile(x,y,s) ;

            if (checkForWinner("X")) winner = playerOne.getSymbol();
            if (checkForWinner("O")) winner = playerTwo.getSymbol();
            checkForDraw();
            swapCurrentPlayer();
        }
    }
    public void makeMoveAi(int x, int y, String playerSymbol) {

        // invalid move
        if (x < 0 || x >= 3) return;
        if (y < 0 || y >= 3) return;

        // invalid player
        if (!isValidPlayer(playerSymbol)) return;

        if (started && !disconnect && !gameOver() && currentPlayer.equals(playerSymbol) && board.tiles[x][y].equals("")) {

            String s = playerSymbol.equals(playerOne.getSymbol()) ? "X" : "O";
            board.setTile(x,y,s) ;
//            if(algo.isMoveLeft(board.getTiles())){
//                MoveModel m = algo.findBestMove(board.getTiles(),playerOne,playerTwo);
//                board.setTile(m.x,m.y,"O");
//            }
            if(!checkForWinner("X")){
                MoveModel m = algo.findBestMove(board.getTiles(),playerOne,playerTwo);
                m = algo.findBestMove(board.getTiles(),playerOne,playerTwo);
                board.setTile(m.x,m.y,"O");
            }

            if (checkForWinner("X")) winner = playerOne.getSymbol();
            if (checkForWinner("O")) winner = playerTwo.getSymbol();
            checkForDraw();
//            swapCurrentPlayer();

        }
    }

    private boolean checkForWinner(String playerSymbol) {
        for (int i = 0; i < 3; i++) {
            if (checkRow(i, playerSymbol)) return true;
        }

        for (int i = 0; i < 3; i++) {
            if (checkColumn(i, playerSymbol)) return true;
        }

        if (checkDiagonal(playerSymbol)) return true;

        return false;
    }

    private boolean checkRow(int i, String playerSymbol) {
        if (board.getTile(i,0).equals(playerSymbol) && board.getTile(i,1).equals(playerSymbol) && board.getTile(i,2).equals(playerSymbol)) {
            return true;
        }

        return false;
    }

    private boolean checkColumn(int i, String playerSymbol) {
        if (board.getTile(0,i).equals(playerSymbol) && board.getTile(1,i).equals(playerSymbol) && board.getTile(2,i).equals(playerSymbol)) {
            return true;
        }

        return false;
    }

    private boolean checkDiagonal(String playerSymbol) {
        if (board.getTile(0,0).equals(playerSymbol) && board.getTile(1,1).equals(playerSymbol) && board.getTile(2,2).equals(playerSymbol)) {
            return true;
        }

        if (board.getTile(0,2).equals(playerSymbol) && board.getTile(1,1).equals(playerSymbol) && board.getTile(2,0).equals(playerSymbol)) {
            return true;
        }

        return false;
    }

    private void checkForDraw() {
        draw = Arrays.stream(board.getTiles()).flatMap(x -> Arrays.stream(x)).noneMatch(x -> x.equals(""));
    }

    private void resetBoard() {
        for(String[] row : board.getTiles()) Arrays.fill(row, "");
    }

    private void swapCurrentPlayer() {
        if (currentPlayer.equals(playerOne.getSymbol())) {
            currentPlayer = playerTwo.getSymbol();
        }
        else {
            currentPlayer = playerOne.getSymbol();
        }
    }

    private boolean isValidPlayer(String playerSymbol) {
        return playerSymbol.equals(playerOne.getSymbol()) || playerSymbol.equals(playerTwo.getSymbol());
    }

    private boolean isValidPlayerId(String playerId) {
        return playerId.equals(playerOne.getId()) || playerId.equals(playerTwo.getId());
    }

    private boolean gameOver() {
        return winner != null || draw;
    }

    @Override
    public String toString() {
        return String.format(
                "GameState[id=%s, player1='%s', player2='%s']",
                id, playerOne.getId(), playerTwo.getId());
    }

}