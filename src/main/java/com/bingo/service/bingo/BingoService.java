package com.bingo.service.bingo;

import com.bingo.model.bingo.Bingo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Esta interface {@link BingoService} contiene los métodos para crear y listar bingos.
 *
 * @author Gian Castro
 * @version 1.0
 */
public interface BingoService {

    /**
     * Este método crea un bingo.
     *
     * @param bingo Representa el objeto bingo con los datos necesarios para crear.
     * @return Devuelve una cadena con el mensaje de creación.
     */
    Mono<String> crearBingo(Mono<Bingo> bingo);

    /**
     * Este método lista los bingos.
     *
     * @return Devuelve la lista de bingos creados.
     */
    Flux<Bingo> listarBingo();
}