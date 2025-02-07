package com.bingo.repository.carton;

import com.bingo.model.carton.Carton;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartonRepository extends ReactiveMongoRepository<Carton, String> {
}