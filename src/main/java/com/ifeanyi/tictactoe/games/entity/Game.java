package com.ifeanyi.tictactoe.games.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "users")
public class Game {

    @Id
    private String id;
    private String firstPlayerId;
    private String secondPlayerId;
    private State state;
    private Date createdAt;
    private Integer[][] board = new Integer[3][3];
    private String winnerId;
    private Boolean draw;

}
