package domain;

public class HumanPlayer extends Player{
    private int id;
    private String name;
    private int wins;
    private int losses;
    private int draw;
    private String symbol;

    @Override
    public void takeTurn() {
        //do something
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    public HumanPlayer(int id, String name, int wins, int losses, int draw, String symbol) {
        this.id = id;
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.draw = draw;
        this.symbol = symbol;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }


}
