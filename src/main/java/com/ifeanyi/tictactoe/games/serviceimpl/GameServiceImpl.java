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
import org.springframework.messaging.simp.SimpMessagingTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final SimpMessagingTemplate messagingTemplate;

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

        if (savedGame.getBoard()[playGame.getX()][playGame.getY()] != 0) {
            throw new InvalidMoveException("Move already played");
        }

       int[][] board = savedGame.getBoard();
       board[playGame.getX()][playGame.getY()] = playGame.getMove();
       savedGame.setBoard(board);

        if (checkIfWin(savedGame.getBoard(), playGame.getMove())) {
            savedGame.setWinnerId(playGame.getWhoIsPlayingId());
            savedGame.setState(State.FINISHED);
            savedGame.setEndedAt(new Date());
        }

       Game updatedGame = update(playGame.getGameId(), savedGame);

        String sendSocketDataToId;

        if (playGame.getWhoIsPlayingId().equals(updatedGame.getFirstPlayerId())){
            sendSocketDataToId = updatedGame.getFirstPlayerId();
        }else {
            sendSocketDataToId = updatedGame.getSecondPlayerId();
        }

        messagingTemplate.convertAndSendToUser(sendSocketDataToId,"/queue/gameplay",updatedGame);

        return updatedGame;
    }

    private boolean checkIfWin(int[][] board, Integer move) {

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
//{
//        "gameId":"664bf8d1d9eb5b535c7ed2da",
//        "whoIsPlayingId":"664be3c95a0ac92050489e52",
//        "x":1,
//        "y":0,
//        "move":1
//
//}