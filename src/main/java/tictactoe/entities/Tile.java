package tictactoe.entities;


public class Tile {

    private int id;
    private int row;
    private int col;
    private Player occupiedBy = null;
    private String symbol;

    public Tile(int id, int row, int col) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.symbol = "_";
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setOccupiedBy(Player player) {
        this.occupiedBy = player;
        this.symbol = player.getSymbol();
    }

    public boolean checkOccupied() {
        if (occupiedBy != null) {
            return true;
        } else {
            return false;
        }
    }

    public String getSymbol(){
        return this.symbol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {

        if (occupiedBy == null) {
            return Integer.toString(this.id);
        } else {
            return occupiedBy.getSymbol();
        }

    }

}