package com.bingo.repository.numerobingo;

import com.bingo.model.numerobingo.NumeroBingo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface NumeroBingoRepository extends ReactiveMongoRepository<NumeroBingo, String> {

    Mono<NumeroBingo> findNumeroBingoByBingoId(String bingoId);
}