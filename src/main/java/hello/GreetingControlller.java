package hello;

import domain.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingControlller {

    GameState logic = new GameState();

    @MessageMapping("/hello")
    @SendTo("/tictactoe/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(100); // simulated delay

        if (logic.getIsReady()) {
            return makeMove(message);
        } else {
            logic.getReady();
            return makeMove(message);
        }
    }

    public Greeting makeMove(HelloMessage message) {
//        if (message.getName().equals("X")) {
//            return new Greeting("Movement = " + HtmlUtils.htmlEscape(message.getName()) + logic.printBoard());
//        }
        return new Greeting(" - " + HtmlUtils.htmlEscape(logic.turnHumanPlayer(message.getName())));
    }



}

