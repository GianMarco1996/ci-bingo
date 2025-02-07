package com.bingo.service.numerobingo;

import com.bingo.model.numerobingo.NumeroBingoDTO;
import com.bingo.model.numerobingo.NumeroBingoValidarDTO;
import reactor.core.publisher.Mono;

/**
 * Esta interface {@link NumeroBingoService} contiene los métodos para obtener, listar validar
 * los números del bingo.
 *
 * @author Gian Castro
 * @version 1.0
 */
public interface NumeroBingoService {

    /**
     * Este método obtiene un numero aleatorio para el juego del bingo.
     *
     * @param bingoId Representa el ID del bingo.
     * @return Devuelve un entero con el número obtenido.
     */
    Mono<Integer> obtenerNumero(String bingoId);

    /**
     * Este método lista los números aleatorios obtenidos para el juego del bingo.
     *
     * @param bingoId Representa el ID del bingo.
     * @return Devuelve los números obtenidos.
     */
    Mono<NumeroBingoDTO> listarNumero(String bingoId);

    /**
     * Este método valida los números obtenidos con la cartilla del jugador y
     * verificar si gano el juego o no.
     *
     * @param numeroBingoValidar Representa el objeto con los datos necesarios para realizar la validación.
     * @return Devuelve una cadena con el resultado de la validación.
     */
    Mono<String> validarNumerosBingo(Mono<NumeroBingoValidarDTO> numeroBingoValidar);
}