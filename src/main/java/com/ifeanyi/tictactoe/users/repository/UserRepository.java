package com.ifeanyi.tictactoe.users.repository;

import com.ifeanyi.tictactoe.users.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {



}
