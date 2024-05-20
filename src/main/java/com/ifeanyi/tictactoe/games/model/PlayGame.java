package com.ifeanyi.tictactoe.games.model;

import lombok.Data;

@Data
public class PlayGame {

    private String gameId;
    private String whoIsPlayingId;
    private Integer x;
    private Integer y;

}
