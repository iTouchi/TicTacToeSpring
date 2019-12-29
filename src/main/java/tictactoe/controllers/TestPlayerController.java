package tictactoe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tictactoe.entities.TestPlayer;
import tictactoe.repositories.TestPlayerRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/player")
public class TestPlayerController {

    private TestPlayerRepository exampleRepository;

    @Autowired
    public void setExampleRepository(TestPlayerRepository exampleRepository){
        this.exampleRepository = exampleRepository;
    }

    @GetMapping()
    public List<TestPlayer> getAllExamples() {
        return exampleRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<TestPlayer> getExample(@PathVariable String id){
        return exampleRepository.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addExample(@RequestBody TestPlayer player){
        exampleRepository.save(player);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateExample(@PathVariable String id, @RequestBody TestPlayer player){
        exampleRepository.save(player);
    }

    @DeleteMapping(path = "{id}")
    public void deleteExample(@PathVariable String id){
        exampleRepository.deleteById(id);
    }
}