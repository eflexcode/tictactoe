package com.ifeanyi.tictactoe.games.repository;

import com.ifeanyi.tictactoe.games.entity.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends MongoRepository<Game,String > {



}
