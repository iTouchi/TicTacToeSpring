package tictactoe.entities;

public class Player {

    private String Id;
    private String symbol;

    public Player(){}

    public Player(String symbol) {
        this.symbol = symbol;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString(){
        return this.getId();
    }

}
