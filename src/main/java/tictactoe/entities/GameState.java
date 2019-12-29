package tictactoe.entities;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GameState {
    //State
    static Boolean isReady = false;

    public static Boolean getIsReady() {
        return isReady;
    }

    public static void setIsReady(Boolean isReady) {
        GameState.isReady = isReady;
    }

    //Other stuff
    static Scanner userInput;

    // Game elements
    static Player playerOne;
    static Player playerTwo;
    static Board board;
    static Session gameSession;
    //    static Tile[] arrayTiles;
    static String[][] stringTiles;
    static ArrayList<Tile> tiles;
    static int startingPlayer;

    // Gameplay stuff
    static Player turn;
    static boolean allTilesOccupied;
    static String winner = null;

    // Algorithm
    static Algorithm algorithm;


    public void getReady() {
        // Determine starting player
        // 0.Player, 1.AI
        Random rand = new Random();
        // Obtain a number between [0 - 1].
        //startingPlayer = rand.nextInt(2);
        startingPlayer = 0;

        if (startingPlayer == 0) {
            playerOne = new HumanPlayer(0, "Jan", "X");
            playerTwo = new HumanPlayer(1, "Ai",  "O");
        }

        board = new Board();
        gameSession = new Session(playerOne, playerTwo, board, 1);
        stringTiles = board.getStringTiles();
        tiles = board.getTiles();
        turn = playerOne;
        allTilesOccupied = false;
        algorithm = new Algorithm();

        isReady = true;
    }



    public void turnAiPlayer() {

        if (turn == playerTwo) {
            System.out.println("The Ai is calculating movement...");

            Move move = algorithm.findBestMove(board.getStringTiles(), playerOne, playerTwo);
            int id = findTile(move.row, move.col);

            System.out.println("...movement is calculated.");

            if (!(tiles.get(id).checkOccupied())) {
                tiles.get(id).setOccupiedBy(playerTwo);
                printBoard();
                System.out.println("Ai has placed his symbol on row: " + move.row + " col: " + move.col);
                winner = checkWinner();
                turn = playerOne;
            }
        }

    }


    public String turnHumanPlayer(String _numinput) {

        userInput = new Scanner(_numinput);

        if (startingPlayer == 0) {
            if (turn == playerOne) {
                return makemove(playerOne, playerTwo, _numinput);
            } else {
                return makemove(playerTwo, playerOne, _numinput);
            }

        }

        return "Wejow";
    }

    public String makemove(Player thisPlayer, Player otherPlayer, String _numinput) {
        while (turn == thisPlayer) {
            int numInput;
            try {
                numInput = userInput.nextInt();

                if (!(numInput >= 0 && numInput <= 8)) {
                    System.out.println("Invalid input; re-enter slot number:");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input; re-enter slot number:");
                continue;
            }

            //(arrayTiles[numInput].getId() == numInput) <- deze zat er bij om een of andere redenen
            if (!tiles.get(numInput).checkOccupied()) {
                if (turn.equals(thisPlayer)) {
                    tiles.get(numInput).setOccupiedBy(thisPlayer);
                    printBoard();
                    winner = checkWinner();
                    if (winner == null){
                        turn = otherPlayer;
                        return thisPlayer.getName() + " placed a " + thisPlayer.getSymbol() + " on " + Integer.parseInt(_numinput);
                    } else{
                        return winner;
                    }

                }
            } else {
                //System.out.println("Slot already taken; re-enter slot number:");
                return "Slot already taken please re-enter slot number:";
            }
        }
        return "Nejo";
    }


    public int findTile(int row, int col) {
        for (Tile t : tiles) {
            if (t.getRow() == row && t.getCol() == col) {
                return t.getId();
            }
        }
        return -1;
    }

    public String[][] printBoard() {

        System.out.println("/---|---|---\\");
        System.out.println("| " + tiles.get(0).getSymbol() + " | " + tiles.get(1).getSymbol() + " | " + tiles.get(2).getSymbol() + " |");
        System.out.println("|-----------|");
        System.out.println("| " + tiles.get(3).getSymbol() + " | " + tiles.get(4).getSymbol() + " | " + tiles.get(5).getSymbol() + " |");
        System.out.println("|-----------|");
        System.out.println("| " + tiles.get(6).getSymbol() + " | " + tiles.get(7).getSymbol() + " | " + tiles.get(8).getSymbol() + " |");
        System.out.println("/---|---|---\\");


        stringTiles = new String[][]{
                {tiles.get(0).getSymbol(), tiles.get(1).getSymbol(), tiles.get(2).getSymbol()},
                {tiles.get(3).getSymbol(), tiles.get(4).getSymbol(), tiles.get(5).getSymbol()},
                {tiles.get(6).getSymbol(), tiles.get(7).getSymbol(), tiles.get(8).getSymbol()}
        };
        board.setStringTiles(stringTiles);

        return stringTiles;

    }

    public void checkTiles() {
        for (int i = 0; i < 9; ) {
            allTilesOccupied = true;
            if (!tiles.get(i).checkOccupied()) {
                allTilesOccupied = false;
                break;
            } else {
                i++;
            }
        }
    }

    public void algoritmeTest() {
        long start = System.currentTimeMillis();

        String[][] b = {
                {"O", "_", "X"},
                {"_", "X", "_"},
                {"_", "_", "_"}
        };

        Move bestMove = algorithm.findBestMove(b, playerOne, playerTwo);

        System.out.println("The Optimal Move is : ");
        System.out.println("Row: " + bestMove.row + " Col: " + bestMove.col);

        long finish = System.currentTimeMillis();

        long timeElapsed = finish - start;

        System.out.println("Calculation time in MS = " + timeElapsed);
    }


    public String checkWinner() {

        checkTiles();

        for (int a = 0; a < 8; a++) {
            String line = null;
            switch (a) {
                case 0:
                    line = tiles.get(0).toString() + tiles.get(1) + tiles.get(2);
                    break;
                case 1:
                    line = tiles.get(3).toString() + tiles.get(4) + tiles.get(5);
                    break;
                case 2:
                    line = tiles.get(6).toString() + tiles.get(7) + tiles.get(8);
                    break;
                case 3:
                    line = tiles.get(0).toString() + tiles.get(3) + tiles.get(6);
                    break;
                case 4:
                    line = tiles.get(1).toString() + tiles.get(4) + tiles.get(7);
                    break;
                case 5:
                    line = tiles.get(2).toString() + tiles.get(5) + tiles.get(8);
                    break;
                case 6:
                    line = tiles.get(0).toString() + tiles.get(4) + tiles.get(8);
                    break;
                case 7:
                    line = tiles.get(2).toString() + tiles.get(4) + tiles.get(6);
                    break;
            }
            if (line.equals(playerOne.getSymbol() + playerOne.getSymbol() + playerOne.getSymbol())) {
                return "The winner is " + playerOne.getName();
            } else if (line.equals(playerTwo.getSymbol() + playerTwo.getSymbol() + playerTwo.getSymbol())) {
                return "The winner is " + playerTwo.getName();
            } else if (allTilesOccupied) {
                return "The game ended in a draw";
            }
        }

        System.out.println(turn.getName() + "'s turn; enter a slot number to place " + turn.getSymbol() + " userInput:");
        return null;
    }
}