package com.bingo.controller.numerobingo;

import com.bingo.api.NumeroApi;
import com.bingo.mapper.numerobingo.NumeroBingoMapper;
import com.bingo.model.NumeroRequest;
import com.bingo.model.NumerosResponse;
import com.bingo.service.numerobingo.NumeroBingoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Esta clase {@link NumeroBingoController} implementa la interfaz {@link NumeroApi} que expone los métodos
 * de obtener, listar validar los números del bingo.
 *
 * @see NumeroApi
 * @author Gian Castro
 * @version 1.0
 */
@RestController
public class NumeroBingoController implements NumeroApi {

    /**
     * Se realiza una inyección de dependencia.
     */
    private final NumeroBingoService numeroBingoService;

    /**
     * Se realiza una inyección de dependencia.
     */
    private final NumeroBingoMapper numeroBingoMapper;

    /**
     * Constructor que recibe las instancias de {@link NumeroBingoService} y {@link NumeroBingoMapper} se conoce
     * como inyección de dependencia por constructores.
     */
    public NumeroBingoController(NumeroBingoService numeroBingoService, NumeroBingoMapper numeroBingoMapper) {
        this.numeroBingoService = numeroBingoService;
        this.numeroBingoMapper = numeroBingoMapper;
    }

    /**
     * Este método lista los números aleatorios obtenidos para el juego del bingo.
     *
     * @param bingoId Representa el ID del bingo.
     * @param exchange .
     * @return Devuelve un ResponseEntity con el valor del resultado.
     */
    @Override
    public Mono<ResponseEntity<NumerosResponse>> listarNumero(String bingoId, ServerWebExchange exchange) {
        return numeroBingoService.listarNumero(bingoId)
                .map(numeroBingoMapper::dtoAModelo)
                .map(ResponseEntity::ok);
    }

    /**
     * Este método valida los números obtenidos con la cartilla del jugador y
     * verificar si gano el juego o no.
     *
     * @param numeroRequest Representa el objeto con los datos necesarios para realizar la validación.
     * @param exchange .
     * @return Devuelve un ResponseEntity con el valor del resultado.
     */
    @Override
    public Mono<ResponseEntity<Object>> validarNumerosBingo(Mono<NumeroRequest> numeroRequest, ServerWebExchange exchange) {
        return numeroBingoService.validarNumerosBingo(numeroRequest.map(numeroBingoMapper::modeloADto))
                .map(ResponseEntity::ok);
    }

    /**
     * Este método obtiene un numero aleatorio para el juego del bingo.
     *
     * @param bingoId Representa el ID del bingo.
     * @param exchange .
     * @return Devuelve un ResponseEntity con el valor del resultado.
     */
    @Override
    public Mono<ResponseEntity<Object>> obtenerNumero(String bingoId, ServerWebExchange exchange) {
        return numeroBingoService.obtenerNumero(bingoId)
                .map(ResponseEntity::ok);
    }
}