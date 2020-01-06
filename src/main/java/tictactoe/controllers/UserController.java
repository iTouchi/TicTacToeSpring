package tictactoe.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/user")
public class UserController {

// this class is for OAuth

    @GetMapping("/")
    public Principal showPrincipal(Principal principal){
        return principal;
    }

}