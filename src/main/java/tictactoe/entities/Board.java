package tictactoe.entities;

import java.util.ArrayList;

public class Board {
    //private ArrayList<Tile> arrayTiles;
    private String[][] stringTiles;
    private Tile[] arrayTiles;
    private ArrayList<Tile> tiles;

    public Board() {
        //arrayTiles = new ArrayList<>();
        stringTiles = new String[][]{
                {"_", "_", "_"},
                {"_", "_", "_"},
                {"_", "_", "_"}
        };

        tiles = new ArrayList<Tile>();
        createArrayTiles();

        //arrayTiles = createArrayTiles();
    }

    public void createArrayTiles() {
        int i = 0;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Tile ts = new Tile(i, row, col);
                tiles.add(ts);
                i++;
            }
        }
    }


    public void takeOneTurn(Player player, Tile tile) {
        tile.setOccupiedBy(player);
    }

    public Tile[] getArrayTiles() {
        return arrayTiles;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public String[][] getStringTiles() {
        return stringTiles;
    }

    public void setStringTiles(String[][] stringTiles) {
        this.stringTiles = stringTiles;

    }
}
