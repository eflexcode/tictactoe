package com.ifeanyi.tictactoe.users.service;

import com.ifeanyi.tictactoe.exception.NotFoundException;
import com.ifeanyi.tictactoe.users.entity.User;
import com.ifeanyi.tictactoe.users.model.CreateUserModel;
import com.ifeanyi.tictactoe.users.model.UserModel;

public interface UserService {

     User create(CreateUserModel createUserModel);

     User update(String id,UserModel userModel) throws NotFoundException;

     User get(String id) throws NotFoundException;

     void delete(String id) throws NotFoundException;

}
