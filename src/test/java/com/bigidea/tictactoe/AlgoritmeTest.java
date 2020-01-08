package com.bigidea.tictactoe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.entities.Cat;
import tictactoe.entities.Player;

import javax.xml.catalog.Catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlgoritmeTest {

    private Cat _cat;

    @BeforeEach
    void setUp() {
        //Arrange for each test
        _cat = new Cat("Kitty", "Bites");
    }

    @Test
    private void setName() {

        // Arrange
        String expected = "Simba";

        // Act
        _cat.setName(expected);
        String actual = _cat.getName();

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

