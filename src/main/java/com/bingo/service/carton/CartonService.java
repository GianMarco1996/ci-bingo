package com.bingo.service.carton;

import com.bingo.model.carton.Carton;
import com.bingo.model.carton.CartonDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Esta interface {@link CartonService} contiene los métodos para generar y obtener los cartones.
 *
 * @author Gian Castro
 * @version 1.0
 */
public interface CartonService {

    /**
     * Este método genera un carton del bingo.
     *
     * @param carton Representa el objeto carton con los datos necesarios para generarlo.
     * @return Devuelve una cadena con el mensaje de creación.
     */
    Mono<String> generarCarton(Mono<Carton> carton);

    /**
     * Este método obtiene los cartones.
     *
     * @return Devuelve los cartones generados.
     */
    Flux<CartonDTO> obtenerCarton();
}