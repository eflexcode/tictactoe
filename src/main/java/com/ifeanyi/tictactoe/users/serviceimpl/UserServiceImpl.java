package com.ifeanyi.tictactoe.users.serviceimpl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifeanyi.tictactoe.exception.NotFoundException;
import com.ifeanyi.tictactoe.users.entity.Status;
import com.ifeanyi.tictactoe.users.entity.User;
import com.ifeanyi.tictactoe.users.model.CreateUserModel;
import com.ifeanyi.tictactoe.users.model.UserModel;
import com.ifeanyi.tictactoe.users.repository.UserRepository;
import com.ifeanyi.tictactoe.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(CreateUserModel createUserModel) {

        User user = new User();
        BeanUtils.copyProperties(createUserModel,user);
        user.setStatus(Status.ONLINE);

        return userRepository.save(user);
    }

    @Override
    public User update(String id,UserModel userModel) throws NotFoundException {

        User savedUser = get(id);
        savedUser.setUsername(userModel.getUsername() != null ? userModel.getUsername() : savedUser.getUsername());
        savedUser.setPassword(userModel.getPassword() != null ? userModel.getPassword() : savedUser.getPassword());
        savedUser.setImageUrl(userModel.getImageUrl() != null ? userModel.getImageUrl() : savedUser.getImageUrl());
        savedUser.setCountry(userModel.getCountry() != null ? userModel.getCountry() : savedUser.getCountry());
        savedUser.setDescription(userModel.getDescription() != null ? userModel.getDescription() : savedUser.getDescription());
        savedUser.setGamesPlayed(userModel.getGamesPlayed() != null ? userModel.getGamesPlayed() : savedUser.getGamesPlayed());
        savedUser.setGamesWon(userModel.getGamesWon() != null ? userModel.getGamesWon() : savedUser.getGamesWon());
        savedUser.setGamesLost(userModel.getGamesLost() != null ? userModel.getGamesLost() : savedUser.getGamesLost());
        savedUser.setGamesDraw(userModel.getGamesDraw() != null ? userModel.getGamesDraw() : savedUser.getGamesDraw());
        savedUser.setStatus(userModel.getStatus() != null ? userModel.getStatus() : savedUser.getStatus());

        return userRepository.save(savedUser);
    }

    @Override
    public User get(String id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException("No User Found with id: "+id));
    }

    @Override
    public void delete(String id) throws NotFoundException {
        userRepository.delete(get(id));
    }

}
