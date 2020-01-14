package tictactoe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import tictactoe.entities.GameStateModel;
import tictactoe.repositories.GameStateRepository;
import tictactoe.entities.PlayerJoinMessage;
import tictactoe.entities.PlayerMoveMessage;

import java.util.Optional;

@Controller
public class GameController {

    @Autowired
    private GameStateRepository repository;

    @MessageMapping("/move/{id}")
    @SendTo("/tictactoe/gamestate/{id}")
    public GameStateModel gamestate(@DestinationVariable String id, PlayerMoveMessage move) throws Exception {

        Optional<GameStateModel> game = repository.findById(id);

        game.get().makeMove(move.getX(), move.getY(), move.getPlayer());
        repository.save(game.get());

        return game.get();
    }

    @MessageMapping("/moveAi/{id}")
    @SendTo("/tictactoe/gamestate/{id}")
    public GameStateModel gamestateAi(@DestinationVariable String id, PlayerMoveMessage move) throws Exception {

        Optional<GameStateModel> game = repository.findById(id);

        game.get().makeMoveAi(move.getX(), move.getY(), move.getPlayer());
        repository.save(game.get());

        return game.get();
    }

    @MessageMapping("join/{id}")
    public void join(SimpMessageHeaderAccessor headerAccessor, @DestinationVariable String id, PlayerJoinMessage message) {
        Optional<GameStateModel> game = repository.findById(id);

        game.get().websocketJoin(message.getPlayer(), headerAccessor.getSessionId());

        GameStateModel gsm = game.get();
        repository.save(gsm);
    }

}
