package tictactoe.entities;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class Algorithm {
    static int MAX = 1000;
    static int MIN = -1000;


    // Returns a values based on who is winning
    // board[3][3] is the Tic-Tac-Toe board
    public int evaluate(String[][] board, int depth) {

        // Checking for horizontal victory
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == "X")
                    return +10 - depth;
                if (board[row][0] == "O")
                    return -10 + depth;
            }
        }

        // Checking for vertical victory
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == "X")
                    return +10;
                if (board[0][col] == "O")
                    return -10;
            }
        }

        // Checking for diagonal victory
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == "X")
                return +10;
            if (board[0][0] == "O")
                return -10;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == "X")
                return +10;
            if (board[0][2] == "O")
                return -10;
        }
        // Else if none has won return 0 for Draw
        return 0;
    }

    //Check if there are moves remaining
    public boolean isMoveLeft(String[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == "_") {
                    return true;
                }
            }
        }
        return false;
    }

    // The minimax algorithm
    public int minimax(String[][] board, int depth, boolean isMax, Player playerOne, Player playerTwo) {
        int score = evaluate(board, depth);

        // If Maximizer has won the gameSession will return the evaluated score for the Maximizer
        if (score == 10) {
            return score;
        }
        // If Minimizer has won the gameSession will return the evaluated score for the Minimizer
        if (score == -10) {
            return score;
        }

        // If there are no moves left and there is no winner the gameSession will return a tie
        if (!isMoveLeft(board)) { //CHANGED was (isMoveLeft(board)==false)
            return 0;
        }

        // If it's the Maximizer's turn
        if (isMax) {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == "_") {
                        // Make the move
                        board[i][j] = playerOne.getSymbol();

                        // Call minimax recursively and choose the max value
                        best = max(best, minimax(board, depth + 1, !isMax, playerOne, playerTwo));//CHANGED was !isMax

                        // Undo the move
                        board[i][j] = "_";
                    }
                }
            }
            return best;
        }
        // If it's the Minimizer's turn
        else {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == "_") {
                        // Make the move
                        board[i][j] = playerTwo.getSymbol();

                        // Call minimax recursively and choose the min value
                        best = min(best, minimax(board, depth + 1, !isMax, playerOne, playerTwo));

                        // Undo the move
                        board[i][j] = "_";
                    }
                }
            }
            return best;
        }
    }

    // Find the move with the best value
    public MoveModel findBestMove(String[][] board, Player playerOne, Player playerTwo) {
        int bestVal = -1000;
        MoveModel bestMove = new MoveModel();
        bestMove.x = -1;
        bestMove.y = -1;

        // Traverse all cells, evaluate minimax function for all empty cells.
        // Return the cell with with the optimal value.

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (board[i][j] == "_") {
                    // Makle the move
                    board[i][j] = playerOne.getSymbol();

                    // Compute evaluation function for this move.
                    int moveVal = minimax(board, 0, false, playerOne, playerTwo);

                    // Undo the move
                    board[i][j] = "_";

                    // If the value of the current move is more than the best value
                    // update best value.
                    if (moveVal > bestVal) {
                        bestMove.x = i;
                        bestMove.y = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        System.out.println("The value of the best move is : " + bestVal);
        return bestMove;
    }


}
