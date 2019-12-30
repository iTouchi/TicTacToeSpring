package tictactoe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;
import tictactoe.entities.HumanPlayer;
import tictactoe.entities.User;
import tictactoe.repositories.TestPlayerRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private TestPlayerRepository exampleRepository;

    @Autowired
    public void setExampleRepository(TestPlayerRepository exampleRepository){
        this.exampleRepository = exampleRepository;
    }

    @GetMapping()
    public List<User> getAllExamples() {
        return exampleRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<User> getExample(@PathVariable String id){
        return exampleRepository.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addExample(@RequestBody User player){
        exampleRepository.save(player);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateExample(@PathVariable String id, @RequestBody User player){
        exampleRepository.save(player);
    }

    @DeleteMapping(path = "{id}")
    public void deleteExample(@PathVariable String id){
        exampleRepository.deleteById(id);
    }
}