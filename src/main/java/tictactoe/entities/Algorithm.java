package tictactoe.entities;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class Algorithm {
//    static int MAX = 1000;
//    static int MIN = -1000;


    // Returns a values based on who is winning
    // board[3][3] is the Tic-Tac-Toe board
    public int evaluate(String[][] board, String p1Symbol, String p2Symbol) {

//        // Checking for horizontal victory
//        for (int row = 0; row < board.length; row++) {
//            if (board[row][0].equals(board[row][1]) && board[row][1].equals(board[row][2])) {
//                if (board[row][0].equals(p2Symbol))
//                    return +10;
//                if (board[row][0].equals(p1Symbol))
//                    return -10;
//            }
//        }

        // Checking for horizontal victory 2.0
        int p1;
        int p2;

        for (int row = 0; row < board.length; row++) {
            p1 = 0;
            p2 = 0;
            for (int col = 0; col < board.length - 1; col++) {
                if (board[row][col].equals(board[row][col + 1])) {
                    if (board[row][col].equals(p2Symbol))
//                            arr[row] = p2Symbol;
                        p2++;
                    if (board[row][col].equals(p1Symbol))
//                            arr[row] = p1Symbol;
                        p1++;
                    if (p1 == board.length - 1) {
                        return -10;
                    }
                    if (p2 == board.length - 1) {
                        return 10;
                    }
                }
            }
        }

//        // Checking for vertical victory
//        for (int col = 0; col < board.length; col++) {
//            if (board[0][col].equals(board[1][col]) && board[1][col].equals(board[2][col])) {
//                if (board[0][col].equals(p2Symbol))
//                    return +10;
//                if (board[0][col].equals(p1Symbol))
//                    return -10;
//            }
//        }

        // Checking for diagonal victory
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            if (board[0][0].equals(p2Symbol))
                return +10;
            if (board[0][0].equals(p1Symbol))
                return -10;
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            if (board[0][2].equals(p2Symbol))
                return +10;
            if (board[0][2].equals(p1Symbol))
                return -10;
        }
        // Else if none has won return 0 for Draw
        return 0;
    }

    //Check if there are moves remaining
    public boolean isMoveLeft(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].equals("")) {
                    return true;
                }
            }
        }
        return false;
    }

    // The minimax algorithm
    public int minimax(String[][] board, int depth, boolean isMax, String playerOne, String playerTwo) {
        int score = evaluate(board, playerOne, playerTwo);

        // If Maximizer has won the gameSession will return the evaluated score for the Maximizer
        if (score == 10 || score == -10) {
            return score - depth;
        }

        // If there are no moves left and there is no winner the gameSession will return a tie
        if (!isMoveLeft(board)) {
            return 0;
        }

        // If it's the Maximizer's turn
        if (isMax) {
            return turnMaximizer(board, depth, isMax, playerOne, playerTwo);
        }
        // If it's the Minimizer's turn
        else {
            return turnMinimizer(board, depth, isMax, playerOne, playerTwo);
        }
    }

    // Find the move with the best value
    public int[] findBestMove(String[][] board, String playerOne, String playerTwo) {
        int bestVal = -1000;
        MoveModel bestMove = new MoveModel(-1, -1);
        int[] best = new int[2];
        best[0] = -1;
        best[1] = -1;

        // Traverse all cells, evaluate minimax function for all empty cells.
        // Return the cell with with the optimal value.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                // Check if cell is empty
                if (board[i][j].equals("")) {

//                    // Make the move
                    board[i][j] = playerTwo;

                    // Compute evaluation function for this move.
                    int moveVal = minimax(board, 0, false, playerOne, playerTwo); // true false changed

//                    // Undo the move
                    board[i][j] = "";

                    // If the value of the current move is more than the best value
                    // update best value.
                    if (moveVal > bestVal) {
                        best[0] = i;
                        best[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        System.out.println("row :" + best[0] + " col:" + best[1]);

        return best;
    }

    public int turnMaximizer(String[][] board, int depth, boolean isMax, String playerOne, String playerTwo) {
        int best = -1000;

        // Methode(symbol,board,depth,isMax,PlayerOne, PlayerTwo)

        // Traverse all cells
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                // Check if cell is empty
                if (board[i][j].equals("")) {

                    // Make the move
                    board[i][j] = playerTwo;

                    // Call minimax recursively and choose the max value
                    best = max(best, minimax(board, depth + 1, !isMax, playerOne, playerTwo));

                    // Undo the move
                    board[i][j] = "";
                }
            }
        }
        return best;
    }

    public int turnMinimizer(String[][] board, int depth, boolean isMax, String playerOne, String playerTwo) {
        int best = 1000;

        // Traverse all cells
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                // Check if cell is empty
                if (board[i][j].equals("")) {

                    // Make the move
                    board[i][j] = playerOne;

                    // Call minimax recursively and choose the min value
                    best = min(best, minimax(board, depth + 1, !isMax, playerOne, playerTwo));

                    // Undo the move
                    board[i][j] = "";
                }
            }
        }
        return best;
    }

    public static boolean areSame(String arr[]) {
        // Put all array elements in a HashSet
        Set<String> s = new HashSet<>(Arrays.asList(arr));

        // If all elements are same, size of
        // HashSet should be 1. As HashSet contains only distinct values.
        return (s.size() == 1);
    }
}
