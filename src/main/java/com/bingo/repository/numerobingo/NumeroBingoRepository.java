package com.bingo.repository.numerobingo;

import com.bingo.model.numerobingo.NumeroBingo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Esta interface {@link NumeroBingoRepository} hereda los métodos necesarios para interactuar con la base de datos
 * de Mongo db, a la colección numerosBingo.
 *
 * @see ReactiveMongoRepository
 * @author Gian Castro
 * @version 1.0
 */
@Repository
public interface NumeroBingoRepository extends ReactiveMongoRepository<NumeroBingo, String> {

    /**
     * Este método busca números del bingo por el ID del bingo.
     *
     * @param bingoId Representa el ID del bingo.
     * @return Devuelve los números del bingo.
     */
    Mono<NumeroBingo> findNumeroBingoByBingoId(String bingoId);
}