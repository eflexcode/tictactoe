package com.ifeanyi.tictactoe.users.model;

import com.ifeanyi.tictactoe.users.entity.Status;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class CreateUserModel {

    private String username;
    private String email;
    private String password;
    private String imageUrl;
    private String country;
    private String description;

}
