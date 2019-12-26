package domain;

public class Move {

    public int row, col;

    private String content;


    public Move(){

    }

    public Move(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}

