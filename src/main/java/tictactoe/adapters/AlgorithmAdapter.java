package tictactoe.adapters;

import tictactoe.entities.Algorithm;
import tictactoe.entities.BoardState;
import tictactoe.entities.MoveModel;

public class AlgorithmAdapter {

    private Algorithm algo = new Algorithm();

    public MoveModel findBestMove(BoardState boardState) {

        int[] best = algo.findBestMove(boardState.getBoard(), boardState.getPlayerOne().getSymbol(), boardState.getPlayerTwo().getSymbol());

        return new MoveModel(best[0], best[1]);
    }

}
