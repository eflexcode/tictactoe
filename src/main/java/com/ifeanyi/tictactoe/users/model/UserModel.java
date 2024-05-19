package com.ifeanyi.tictactoe.users.model;

import com.ifeanyi.tictactoe.users.entity.Status;
import lombok.Data;

@Data
public class UserModel {

    private String username;
    private String email;
    private String password;
    private Status imageUrl;
    private Status status = Status.ONLINE;
    private Long gamesPlayed = 0L;
    private Long gamesWon = 0L;
    private Long gamesLost = 0L;
    private Long gamesDraw = 0L;

}
