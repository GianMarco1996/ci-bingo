package com.bingo.controller.bingo;

import com.bingo.api.BingoApi;
import com.bingo.mapper.bingo.BingoMapper;
import com.bingo.model.BingoRequest;
import com.bingo.model.BingoResponse;
import com.bingo.service.bingo.BingoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Esta clase {@link BingoController} implementa la interfaz {@link BingoApi} que expone los métodos
 * de crear y listar bingos.
 *
 * @see BingoApi
 * @author Gian Castro
 * @version 1.0
 */
@RestController
public class BingoController implements BingoApi {

    /**
     * Se realiza una inyección de dependencia.
     */
    private final BingoService bingoService;

    /**
     * Se realiza una inyección de dependencia.
     */
    private final BingoMapper bingoMapper;

    /**
     * Constructor que recibe las instancias de {@link BingoService} y {@link BingoMapper} se conoce
     * como inyección de dependencia por constructores.
     */
    public BingoController(BingoService bingoService, BingoMapper bingoMapper) {
        this.bingoService = bingoService;
        this.bingoMapper = bingoMapper;
    }

    /**
     * Este método crea un bingo.
     *
     * @param bingoRequest Representa el objeto bingo con los datos necesarios para crear.
     * @param exchange .
     * @return Devuelve un ResponseEntity con el valor del resultado.
     */
    @Override
    public Mono<ResponseEntity<Object>> crearBingo(Mono<BingoRequest> bingoRequest, ServerWebExchange exchange) {
        return bingoService.crearBingo(bingoRequest.map(bingoMapper::modeloADocumento))
                .map(ResponseEntity::ok);
    }

    /**
     * Este método lista los bingos.
     *
     * @param exchange .
     * @return Devuelve un ResponseEntity con el valor del resultado.
     */
    @Override
    public Mono<ResponseEntity<Flux<BingoResponse>>> listarBingo(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok().body(bingoService.listarBingo()
                .map(bingoMapper::documentoAModelo)));
    }
}