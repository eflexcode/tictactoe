package com.ifeanyi.tictactoe.users.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String username;
    @Indexed(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    private String imageUrl;
    private String country;
    private String description;
    private Status status = Status.ONLINE;
    private Long gamesPlayed = 0L;
    private Long gamesWon = 0L;
    private Long gamesLost = 0L;
    private Long gamesDraw = 0L;

}
