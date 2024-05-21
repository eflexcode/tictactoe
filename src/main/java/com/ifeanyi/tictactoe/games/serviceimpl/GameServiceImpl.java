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

        if (playGame.getX() > 2 || playGame.getY() > 2) {
            throw new InvalidMoveException("Either of these moves " + playGame.getX() + " " + playGame.getY() + " is invalid");
        }

        if (savedGame.getBoard()[playGame.getX()][playGame.getY()] != null) {
            throw new InvalidMoveException("Move already played");
        }

        savedGame.getBoard()[playGame.getX()][playGame.getY()] = playGame.getMove();

        try {
            if (checkIfWin(savedGame.getBoard(), playGame.getMove())) {
                savedGame.setWinnerId(playGame.getWhoIsPlayingId());
                savedGame.setState(State.FINISHED);
            }
        }catch (Exception ignored){

        }

        return update(playGame.getGameId(), savedGame);
    }

    private boolean checkIfWin(Integer[][] board, Integer move) {

        int count = 0; // current player has won

        int[][] winingPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

        for (int i = 0; i <= winingPosition.length; i++) {

            for (int j = 0; j <= winingPosition[i].length; j++) {

                int positionValue = board[i][j];

                if (positionValue == move) {
                    count++;
                    if (count == 3) {
                        return true;
                    }
                }

            }

        }

        return false;
    }

    @Override
    public void delete(String id) throws NotFoundException {
        get(id);
        gameRepository.deleteById(id);
    }
}
