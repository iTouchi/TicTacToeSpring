package com.bigidea.tictactoe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.adapters.AlgorithmAdapter;
import tictactoe.entities.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlgoritmeTest {

    private AlgorithmAdapter _algo;
    private Board _board;
    private String[][] _tiles;
    private MoveModel _moveModel;
    private Player _playerOne;
    private Player _playerTwo;

    @BeforeEach
    void setUp() {
        //Arrange for each test
        _algo = new AlgorithmAdapter();
        _board = new Board(3,3);
        _moveModel = new MoveModel();
        _playerOne = new Player("X");
        _playerTwo = new Player("O");
    }

    @Test
    void testPreventOponnentWinning() {

        // Arrange
        String expected = "O";
        _tiles = new String[][]{
                {"O", "", ""},
                {"X", "X", ""},
                {"", "", ""}
        };
        _board.setTiles(_tiles);

        // Act
        _moveModel = _algo.findBestMove(_board.getTiles(),_playerOne,_playerTwo);
        _board.setTile(_moveModel.x,_moveModel.y,_playerTwo.getSymbol());

        String actual = _board.getTile(1,2);

        // Assert
        assertEquals(expected, actual, "The Algorithm prevents opponent from winning ");
    }

    @Test
    void testWinBeforePrevent() {

        // Arrange
        String expected = "O";
        _tiles = new String[][]{
                {"O", "O", ""},
                {"X", "X", ""},
                {"", "", ""}
        };
        _board.setTiles(_tiles);

        // Act
        _moveModel = _algo.findBestMove(_board.getTiles(),_playerOne,_playerTwo);
        _board.setTile(_moveModel.x,_moveModel.y,_playerTwo.getSymbol());
//        String[][] actual = _board.getTiles();
        String actual = _board.getTile(0,2);

        // Assert
        assertEquals(expected, actual, "The Algorithm prefers to win then to block the opponent ");
    }

    @AfterEach
    void tearDown() {
        _algo = null;
        _board = null;
        _moveModel = null;
        _playerOne = null;
        _playerTwo = null;
    }


}

