package com.ifeanyi.tictactoe.users.serviceimpl;

import com.ifeanyi.tictactoe.users.entity.User;
import com.ifeanyi.tictactoe.users.model.CreateUserModel;
import com.ifeanyi.tictactoe.users.model.UserModel;
import com.ifeanyi.tictactoe.users.repository.UserRepository;
import com.ifeanyi.tictactoe.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(CreateUserModel createUserModel) {
        return null;
    }

    @Override
    public User update(UserModel userModel) {
        return null;
    }

    @Override
    public User get(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
