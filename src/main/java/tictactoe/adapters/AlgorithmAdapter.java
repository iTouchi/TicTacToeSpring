package tictactoe.adapters;

import tictactoe.entities.Algorithm;
import tictactoe.entities.MoveModel;
import tictactoe.entities.Player;

public class AlgorithmAdapter {

    Algorithm algo = new Algorithm();

    public MoveModel findBestMove(String[][] board, Player playerOne, Player playerTwo) {

        int[] besty = algo.findBestMove(board, playerOne.getSymbol(), playerTwo.getSymbol());
        MoveModel bestmove = new MoveModel(besty[0], besty[1]);

        return bestmove;
    }

}
