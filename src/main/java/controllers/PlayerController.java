package controllers;

import domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import repositories.PlayerRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/player")
public class PlayerController {

    private PlayerRepository playerRepository;

    @Autowired
    private void setPlayerRepository(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // Adds new player to the system
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addPlayer(@RequestBody Player player) {
        playerRepository.save(player);
    }

    //update existing player in the database
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePlayer(@PathVariable String id, @RequestBody Player player){
        playerRepository.save(player);
    }

    @GetMapping()
    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }


}
