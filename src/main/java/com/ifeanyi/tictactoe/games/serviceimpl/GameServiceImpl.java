package com.ifeanyi.tictactoe.games.serviceimpl;

import com.ifeanyi.tictactoe.exception.InvalidMoveException;
import com.ifeanyi.tictactoe.exception.NotFoundException;
import com.ifeanyi.tictactoe.games.entity.Game;
import com.ifeanyi.tictactoe.games.model.PlayGame;
import com.ifeanyi.tictactoe.games.repository.GameRepository;
import com.ifeanyi.tictactoe.games.service.GameService;
import com.ifeanyi.tictactoe.users.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Override
    public Game create(Game game) {
        game.setCreatedAt(new Date());
        return gameRepository.save(game);
    }

    @Override
    public Game update(String id, Game sentgame) throws NotFoundException {

        Game savedGame = get(id);
        savedGame.setFirstPlayerId(sentgame.getFirstPlayerId() != null ? sentgame.getFirstPlayerId() : savedGame.getFirstPlayerId());
        savedGame.setSecondPlayerId(sentgame.getSecondPlayerId() != null ? sentgame.getSecondPlayerId() : savedGame.getSecondPlayerId());
        savedGame.setState(sentgame.getState() != null ? sentgame.getState() : savedGame.getState());
        savedGame.setDraw(sentgame.getDraw() != null ? sentgame.getDraw() : savedGame.getDraw());
        savedGame.setWinnerId(sentgame.getWinnerId() != null ? sentgame.getWinnerId() : savedGame.getWinnerId());
        savedGame.setBoard(sentgame.getBoard() != null ? sentgame.getBoard() : savedGame.getBoard());

        return gameRepository.save(savedGame);
    }

    @Override
    public Game get(String id) throws NotFoundException {
        return gameRepository.findById(id).orElseThrow(() -> new NotFoundException("No Game found with game id: " + id));
    }

    @Override
    public Game playGame(PlayGame playGame) throws InvalidMoveException, NotFoundException {

        Game savedGame = get(playGame.getGameId());

        if (playGame.getX() > 2 || playGame.getY() > 2) {
            throw new InvalidMoveException("Either of these moves " + playGame.getX() + " " + playGame.getY() + " is invalid");
        }

        Integer[][] board =  savedGame.getBoard();

       for (int i =0; i<= board.length ;i++){
           if (board[playGame.getX()][playGame.getY()] == i){
               System.out.println("move already played");
           }
       }

        return update(playGame.getGameId(), savedGame);
    }

//    private boolean checkIfWin() {
//
//    }

    @Override
    public void delete(String id) throws NotFoundException {
        get(id);
        gameRepository.deleteById(id);
    }
}
