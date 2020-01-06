package tictactoe.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tictactoe.entities.AvailableGame;
import tictactoe.entities.GameStateModel;
import tictactoe.repositories.GameStateRepository;

@RestController
public class ServerController {

    @Autowired
    private GameStateRepository repository;

    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping(method = RequestMethod.GET, value = "/tictactoe/games")
    public List<AvailableGame> getGames() {
        List<AvailableGame> available = new ArrayList<AvailableGame>();
        List<GameStateModel> games = repository.findByStartedAndDisconnect(false, false);

        for(GameStateModel game : games) {
            available.add(new AvailableGame(game.id, game.name));
        }

        return available;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tictactoe/game")
    public GameStateModel createGame(
            @RequestParam(value = "player") String player,
            @RequestParam(value = "name", defaultValue="A TicTacToe Game") String name) {

        GameStateModel game = new GameStateModel(player, name);
        repository.save(game);

        return game;
    }

    // Join
    @RequestMapping(method = RequestMethod.PATCH, value = "/tictactoe/game", params = {"id", "player"})
    public GameStateModel joinGame(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "player") String player
    ) {

        Optional<GameStateModel> game = repository.findById(id);

        if (!game.get().started && !game.get().disconnect) {
            game.get().join(player);

            repository.save(game.get());
            updateGameState(id, game.get());

            return game.get();
        }

        // return null if third player is trying to join or player left
        return null;
    }

    // Disconnect
    @RequestMapping(method = RequestMethod.PATCH, value = "/tictactoe/game", params = {"id", "player", "disconnect"})
    public GameStateModel disconnectGame(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "player") String player,
            @RequestParam(value = "disconnect") boolean disconnect) {

        Optional<GameStateModel> game = repository.findById(id);

        if (disconnect) {
            game.get().disconnect(player);
            repository.save(game.get());

            updateGameState(id, game.get());

            return game.get();
        }

        return null;
    }

    // Rematch
    @RequestMapping(method = RequestMethod.PATCH, value = "/tictactoe/game", params = {"id", "player", "rematch"})
    public GameStateModel rematchGame(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "player") String player,
            @RequestParam(value = "rematch") boolean rematch) {

        Optional<GameStateModel> game = repository.findById(id);

        if (rematch) {
            game.get().rematch(player);

            repository.save(game.get());
            updateGameState(id, game.get());

            return game.get();
        }

        return null;
    }

    // push new game state to websocket
    private void updateGameState(String id, GameStateModel game) {
        template.convertAndSend("/tictactoe/gamestate/" + id, game);
    }

}
