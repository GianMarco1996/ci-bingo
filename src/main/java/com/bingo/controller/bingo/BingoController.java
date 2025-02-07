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

@RestController
public class BingoController implements BingoApi {

    private final BingoService bingoService;

    private final BingoMapper bingoMapper;

    public BingoController(BingoService bingoService, BingoMapper bingoMapper) {
        this.bingoService = bingoService;
        this.bingoMapper = bingoMapper;
    }

    @Override
    public Mono<ResponseEntity<Object>> crearBingo(Mono<BingoRequest> bingoRequest, ServerWebExchange exchange) {
        return bingoService.crearBingo(bingoRequest.map(bingoMapper::modeloADocumento))
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Flux<BingoResponse>>> listarBingo(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok().body(bingoService.listarBingo()
                .map(bingoMapper::documentoAModelo)));
    }
}