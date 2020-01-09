package tictactoe.entities;

public class Board {

    public String[][] tiles;
    private String name;

    public Board() {
        tiles = new String[3][3];
    }

    public String[][] getTiles() {
        return tiles;
    }

    public void setTiles (String[][] tiles){
        this.tiles = tiles;
    }

    public String getTile (int x, int y){
        return tiles[x][y];
    }

    public void setTile(int x, int y, String s) {
        this.tiles[x][y] = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
