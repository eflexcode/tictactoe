package com.ifeanyi.tictactoe.users.controller;

import com.ifeanyi.tictactoe.exception.NotFoundException;
import com.ifeanyi.tictactoe.users.entity.User;
import com.ifeanyi.tictactoe.users.model.CreateUserModel;
import com.ifeanyi.tictactoe.users.model.UserModel;
import com.ifeanyi.tictactoe.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public User create(@RequestBody CreateUserModel userModel) {
        System.out.println("ssssssssssssssssssssss");
        return userService.create(userModel);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User update(@PathVariable String id, @RequestBody UserModel userModel) throws NotFoundException {
        return userService.update(id, userModel);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User get(@PathVariable String id) throws NotFoundException {
        log.debug("got here uuuuuuuuuu");

        return userService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) throws NotFoundException {
         userService.delete(id);
    }// remember to test if delete return exception

}
