package com.ifeanyi.tictactoe.games.controller;

import com.ifeanyi.tictactoe.exception.GameFinished;
import com.ifeanyi.tictactoe.exception.InvalidMoveException;
import com.ifeanyi.tictactoe.exception.NotFoundException;
import com.ifeanyi.tictactoe.games.entity.Game;
import com.ifeanyi.tictactoe.games.model.JoinGame;
import com.ifeanyi.tictactoe.games.model.PlayGame;
import com.ifeanyi.tictactoe.games.service.GameService;
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
        return gameService.create(game);
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public Game join(@RequestBody JoinGame game) throws NotFoundException, GameFinished {
        return gameService.join(game);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game get(@PathVariable String id) throws NotFoundException {
        return gameService.get(id);
    }


    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) throws NotFoundException {
         gameService.delete(id);
    }

}
