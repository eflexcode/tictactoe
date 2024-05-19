package com.ifeanyi.tictactoe.users.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {

    private String id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private Status imageUrl;
    private String country;
    private String description;
    private Status status = Status.ONLINE;
    private Long gamesPlayed = 0L;
    private Long gamesWon = 0L;
    private Long gamesLost = 0L;
    private Long gamesDraw = 0L;

}
