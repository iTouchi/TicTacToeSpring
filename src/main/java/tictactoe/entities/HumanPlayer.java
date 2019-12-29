package tictactoe.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class HumanPlayer extends Player{

    private int id;
    private String name;
    private String password;
    private String symbol;

    @Override
    public void takeTurn() {
        //do something
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    public HumanPlayer(){

    }

    public HumanPlayer(String name, String password){
        this.name = name;
        this.password = password;
    }

    public HumanPlayer(int id, String name, String symbol) {
        this.id = id;
        this.name = name;
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


    public String getPassword(){
        return this.password;
    }

}
