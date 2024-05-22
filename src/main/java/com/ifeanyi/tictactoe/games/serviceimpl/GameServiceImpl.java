package com.ifeanyi.tictactoe.games.serviceimpl;

import com.ifeanyi.tictactoe.exception.GameFinished;
import com.ifeanyi.tictactoe.exception.InvalidMoveException;
import com.ifeanyi.tictactoe.exception.NotFoundException;
import com.ifeanyi.tictactoe.games.entity.Game;
import com.ifeanyi.tictactoe.games.entity.State;
import com.ifeanyi.tictactoe.games.model.JoinGame;
import com.ifeanyi.tictactoe.games.model.PlayGame;
import com.ifeanyi.tictactoe.games.repository.GameRepository;
import com.ifeanyi.tictactoe.games.service.GameService;
import com.ifeanyi.tictactoe.users.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
    public Game join(JoinGame joinGame) throws NotFoundException, GameFinished {

        Game game = get(joinGame.getGameId());

        if (game.getState() == State.FINISHED) {
            throw new GameFinished("Game is closed");
        }

        game.setState(State.ONGOING);
        game.setSecondPlayerId(joinGame.getUserId());

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

//        if (playGame.getX() > 2 || playGame.getY() > 2) {
//            throw new InvalidMoveException("Either of these moves " + playGame.getX() + " " + playGame.getY() + " is invalid");
//        }
//
//        if (savedGame.getBoard()[playGame.getX()][playGame.getY()] != null) {
//            throw new InvalidMoveException("Move already played");
//        }

        savedGame.getBoard()[playGame.getX()][playGame.getY()] = playGame.getMove();

//        try {
        if (checkIfWin(savedGame.getBoard(), playGame.getMove())) {
            savedGame.setWinnerId(playGame.getWhoIsPlayingId());
            savedGame.setState(State.FINISHED);
            savedGame.setEndedAt(new Date());
        }
//        }catch (Exception ignored){
//            System.out.println("crasheddddddddddddddddddddddddddddddd");
//        }

        return update(playGame.getGameId(), savedGame);
    }

    private boolean checkIfWin(int[][] board, Integer move) {

        int count = 0; // current player has won

        if (board[0][0] == move && board[0][1] == move && board[0][2] == move) {
            return true;
        } else if (board[1][0] == move && board[1][1] == move && board[1][2] == move) {
            return true;
        } else if (board[2][0] == move && board[2][1] == move && board[2][2] == move) {
            return true;
        } else if (board[0][0] == move && board[1][0] == move && board[2][0] == move) {
            return true;
        } else if (board[0][1] == move && board[1][1] == move && board[2][1] == move) {
            return true;
        } else if (board[0][2] == move && board[1][2] == move && board[2][2] == move) {
            return true;
        } else if (board[0][0] == move && board[1][1] == move && board[2][2] == move) {
            return true;
        } else if (board[0][2] == move && board[1][1] == move && board[2][0] == move) {
            return true;
        }

        return false;
    }

    @Override
    public void delete(String id) throws NotFoundException {
        get(id);
        gameRepository.deleteById(id);
    }
}
