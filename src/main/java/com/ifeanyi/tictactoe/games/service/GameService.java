package com.ifeanyi.tictactoe.games.service;

import com.ifeanyi.tictactoe.exception.InvalidMoveException;
import com.ifeanyi.tictactoe.exception.NotFoundException;
import com.ifeanyi.tictactoe.games.entity.Game;
import com.ifeanyi.tictactoe.games.model.PlayGame;

public interface GameService {

    Game create(Game game);

    Game update(String id,Game game) throws NotFoundException;

    Game get(String id) throws NotFoundException;

    Game playGame(PlayGame playGame) throws InvalidMoveException, NotFoundException;

    void delete(String id) throws NotFoundException;

}
