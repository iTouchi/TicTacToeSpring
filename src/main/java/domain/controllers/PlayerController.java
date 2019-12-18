package domain.controllers;

import domain.HumanPlayer;
import hello.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class PlayerController {

    private final AtomicInteger counter = new AtomicInteger();

//    @RequestMapping("/player")
//    public HumanPlayer player(@RequestParam(value ="name", defaultValue = )){
//
//    }

//    @RequestMapping("/greeting")
//    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new Greeting(counter.incrementAndGet(),
//                String.format(template, name));
//    }

}
