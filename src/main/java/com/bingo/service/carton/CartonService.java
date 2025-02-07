package com.bingo.service.carton;

import com.bingo.model.carton.Carton;
import com.bingo.model.carton.CartonDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CartonService {

    Mono<String> generarCarton(Mono<Carton> carton);

    Flux<CartonDTO> obtenerCarton();
}