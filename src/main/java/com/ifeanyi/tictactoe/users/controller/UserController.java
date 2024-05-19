package com.ifeanyi.tictactoe.users.controller;

import com.ifeanyi.tictactoe.exception.NotFoundException;
import com.ifeanyi.tictactoe.users.entity.User;
import com.ifeanyi.tictactoe.users.model.CreateUserModel;
import com.ifeanyi.tictactoe.users.model.UserModel;
import com.ifeanyi.tictactoe.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public User create(@RequestBody CreateUserModel userModel) {
        return userService.create(userModel);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User update(@PathVariable String id, @RequestBody UserModel userModel) throws NotFoundException {
        return userService.update(id, userModel);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User get(@PathVariable String id) throws NotFoundException {
        return userService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) throws NotFoundException {
         userService.delete(id);
    }// remember to test if delete return exception

}
