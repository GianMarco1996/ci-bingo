package com.bingo.service.bingo;

import com.bingo.model.bingo.Bingo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BingoService {

    Mono<String> crearBingo(Mono<Bingo> bingo);

    Flux<Bingo> listarBingo();
}