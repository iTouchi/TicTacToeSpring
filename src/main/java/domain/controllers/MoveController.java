package domain.controllers;

import domain.GameState;
import domain.Move;
import hello.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MoveController {

//    GameState logic = new GameState();
//
//    @MessageMapping("hello")
//    @SendTo("/tictactoe/hgreetings")
//    public Move move (HelloMessage message) throws Exception{
//        Thread.sleep(100);
//
//        if(logic.getReady();{
//            return makeMove(message);
//        } else{
//            logic.getReady();
//            return makeMove(message);
//        }
//    }
//
//    public Move makeMove(HelloMessage message){
//        return new Move();
//    }
}
