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

/**
 * Esta clase {@link CartonController} implementa la interfaz {@link CartonApi} que expone los métodos
 * de generar y obtener los cartones.
 *
 * @see CartonApi
 * @author Gian Castro
 * @version 1.0
 */
@RestController
public class CartonController implements CartonApi {

    /**
     * Se realiza una inyección de dependencia.
     */
    private final CartonService cartonService;

    /**
     * Se realiza una inyección de dependencia.
     */
    private final CartonMapper cartonMapper;

    /**
     * Constructor que recibe las instancias de {@link CartonService} y {@link CartonMapper} se conoce
     * como inyección de dependencia por constructores.
     */
    public CartonController(CartonService cartonService, CartonMapper cartonMapper) {
        this.cartonService = cartonService;
        this.cartonMapper = cartonMapper;
    }

    /**
     * Este método genera un carton del bingo.
     *
     * @param cartonRequest Representa el objeto carton con los datos necesarios para generarlo.
     * @param exchange .
     * @return Devuelve un ResponseEntity con el valor del resultado.
     */
    @Override
    public Mono<ResponseEntity<Object>> generarCarton(Mono<CartonRequest> cartonRequest, ServerWebExchange exchange) {
        return cartonService.generarCarton(cartonRequest.map(cartonMapper::modeloADocumento))
                .map(ResponseEntity::ok);
    }

    /**
     * Este método obtiene los cartones.
     *
     * @param exchange .
     * @return Devuelve un ResponseEntity con el valor del resultado.
     */
    @Override
    public Mono<ResponseEntity<Flux<CartonResponse>>> obtenerCarton(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok().body(cartonService.obtenerCarton()
                .map(cartonMapper::dtoAModelo)));
    }
}