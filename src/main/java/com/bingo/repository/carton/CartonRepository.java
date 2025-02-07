package com.bingo.repository.carton;

import com.bingo.model.carton.Carton;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Esta interface {@link CartonRepository} hereda los métodos necesarios para interactuar con la base de datos
 * de Mongo db, a la colección cartones.
 *
 * @see ReactiveMongoRepository
 * @author Gian Castro
 * @version 1.0
 */
@Repository
public interface CartonRepository extends ReactiveMongoRepository<Carton, String> {
}