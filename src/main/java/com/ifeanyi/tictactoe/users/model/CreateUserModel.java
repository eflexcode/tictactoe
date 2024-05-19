package com.ifeanyi.tictactoe.users.model;

import com.ifeanyi.tictactoe.users.entity.Status;
import lombok.Data;

@Data
public class CreateUserModel {

    private String username;
    private String email;
    private String password;
    private Status imageUrl;

}
