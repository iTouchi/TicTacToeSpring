package tictactoe.entities;

import org.springframework.stereotype.Component;

@Component
public class GameStateMessage {

    private String content;

    public GameStateMessage() {
    }

    public GameStateMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
