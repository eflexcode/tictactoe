package com.ifeanyi.tictactoe.games.controller;

import com.ifeanyi.tictactoe.games.entity.Game;
import com.ifeanyi.tictactoe.games.service.GameService;
import com.ifeanyi.tictactoe.users.entity.User;
import com.ifeanyi.tictactoe.users.model.CreateUserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Game create(@RequestBody Game game) {
        System.out.println("ssssssssssssssssssssss");
        return gameService.create(game);
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public Game join(@RequestBody Game game) {
        System.out.println("ssssssssssssssssssssss");
        return gameService.create(game);
    }
}
