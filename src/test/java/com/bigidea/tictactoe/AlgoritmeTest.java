package com.bigidea.tictactoe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.entities.*;

import javax.xml.catalog.Catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlgoritmeTest {

    private Cat _cat;
    private Algorithm _algo;
    private Board _board;
    private String[][] _tiles;
    private MoveModel _moveModel;
    private Player _playerOne;
    private Player _playerTwo;

    @BeforeEach
    void setUp() {
        //Arrange for each test
        _cat = new Cat("Kitty", "Bites");
        _algo = new Algorithm();
        _board = new Board();
        _moveModel = new MoveModel();
        _playerOne = new Player("X");
        _playerTwo = new Player("O");
    }

    @Test
    void testAlgo() {
        // Arrange
//        String[][] expected = {
//                {"O", "", ""},
//                {"", "", ""},
//                {"", "", ""}
//        };

        String expected = "O";
        _tiles = new String[][]{
                {"X", "", ""},
                {"", "X", ""},
                {"", "", ""}
        };
        _board.setTiles(_tiles);

        // Act
        _moveModel = _algo.findBestMove(_board.getTiles(),_playerOne,_playerTwo);
        _board.setTile(_moveModel.x,_moveModel.y,_playerTwo.getSymbol());
//        String[][] actual = _board.getTiles();
        String actual = _board.getTile(2,2);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void setBadHabits() {

        // Arrange
        String expected = "Scratches";

        // Act
        _cat.setBadHabits(expected);
        String actual = _cat.getBadHabits();

        // Assert
        assertEquals(expected, actual);
    }

    @AfterEach
    void tearDown() {
        _cat = null;
    }


}

