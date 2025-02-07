package com.bingo.repository.bingo;

import com.bingo.model.bingo.Bingo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BingoRepository extends ReactiveMongoRepository<Bingo, String> {
}