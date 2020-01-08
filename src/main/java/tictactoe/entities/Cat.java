package tictactoe.entities;

public class Cat {

    public Cat(String name, String badHabits) {
        this.name = name;
        this.badHabits = badHabits;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBadHabits() {
        return badHabits;
    }

    public void setBadHabits(String badHabits) {
        this.badHabits = badHabits;
    }

    private String badHabits;

}
