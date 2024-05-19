package com.ifeanyi.tictactoe.users.service;

import com.ifeanyi.tictactoe.users.entity.User;
import com.ifeanyi.tictactoe.users.model.CreateUserModel;
import com.ifeanyi.tictactoe.users.model.UserModel;

public interface UserService {

     User create(CreateUserModel createUserModel);

     User update(UserModel userModel);

     User get(String id);

     void delete(String id);

}
