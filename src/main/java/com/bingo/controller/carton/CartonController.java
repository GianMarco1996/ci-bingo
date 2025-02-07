package com.bingo.controller.carton;

import com.bingo.api.CartonApi;
import com.bingo.mapper.carton.CartonMapper;
import com.bingo.model.CartonRequest;
import com.bingo.model.CartonResponse;
import com.bingo.service.carton.CartonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CartonController implements CartonApi {

    private final CartonService cartonService;

    private final CartonMapper cartonMapper;

    public CartonController(CartonService cartonService, CartonMapper cartonMapper) {
        this.cartonService = cartonService;
        this.cartonMapper = cartonMapper;
    }

    @Override
    public Mono<ResponseEntity<Object>> generarCarton(Mono<CartonRequest> cartonRequest, ServerWebExchange exchange) {
        return cartonService.generarCarton(cartonRequest.map(cartonMapper::modeloADocumento))
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Flux<CartonResponse>>> obtenerCarton(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok().body(cartonService.obtenerCarton()
                .map(cartonMapper::dtoAModelo)));
    }
}