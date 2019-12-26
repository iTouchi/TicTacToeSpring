package hello;

import domain.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingControlller {

    GameState logic = new GameState();

    // HTML geeft waarde aan JavaScript
    // JS stuurt JSON naar app/hello via ws
    // met 'name': $("#slotNumber").val()
    // Controller zet JSON om naar HelloMessag welke een atribuut heeft genaamd "name"
    // Hier moet ik de logica laten uitvoeren van de game

    @MessageMapping("/hello")
    @SendTo("/tictactoe/moves")
    public Move move(HelloMessage message) throws Exception {
        Thread.sleep(100); // simulated delay

        if (logic.getIsReady()) {
            return makeMove(message);
        } else {
            logic.getReady();
            return makeMove(message);
        }
    }

    public Move makeMove(HelloMessage message) {
//        if (message.getName().equals("X")) {
//            return new Greeting("Movement = " + HtmlUtils.htmlEscape(message.getName()) + logic.printBoard());
//        }
        //return new Greeting(" - " + HtmlUtils.htmlEscape(logic.turnHumanPlayer(message.getName()))); //HtmlUtils.htmlEscape = Turn special characters into HTML character references
        return new Move(logic.turnHumanPlayer(message.getName()));
    }


}

