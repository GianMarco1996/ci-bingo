package com.bingo.repository.bingo;

import com.bingo.model.bingo.Bingo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Esta interface {@link BingoRepository} hereda los métodos necesarios para interactuar con la base de datos
 * de Mongo db, a la colección bingos.
 *
 * @see ReactiveMongoRepository
 * @author Gian Castro
 * @version 1.0
 */
@Repository
public interface BingoRepository extends ReactiveMongoRepository<Bingo, String> {
}