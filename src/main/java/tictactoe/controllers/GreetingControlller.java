package tictactoe.controllers;

import tictactoe.entities.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingControlller {

    GameState logic = new GameState();

    // HTML geeft waarde aan JavaScript
    // JS stuurt JSON naar app/tictactoe.hello via ws
    // met 'name': $("#slotNumber").val()
    // Controller zet JSON om naar HelloMessag welke een atribuut heeft genaamd "name"
    // Hier moet ik de logica laten uitvoeren van de game

    @MessageMapping("/hello")
    @SendTo("/tictactoe/moves")
    public Move move(Move message) throws Exception {
        Thread.sleep(100); // simulated delay

        if (logic.getIsReady()) {
            return makeMove(message);
        } else {
            logic.getReady();
            return makeMove(message);
        }
    }

    @MessageMapping("/jello")
    public HumanPlayer humanPlayer(HumanPlayer message) throws Exception {
        Thread.sleep(100); // simulated delay

        HumanPlayer hp = new HumanPlayer(message.getName(),message.getPassword());
        System.out.println("UNAME : " + hp.getName() + " PASS : " +  hp.getPassword());
        return hp;
    }


    public Move makeMove(Move message) {

        //return new Greeting(" - " + HtmlUtils.htmlEscape(logic.turnHumanPlayer(message.getName()))); //HtmlUtils.htmlEscape = Turn special characters into HTML character references
        return new Move(logic.turnHumanPlayer(message.getContent()));
    }


}

