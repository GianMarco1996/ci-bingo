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

@RestController
public class NumeroBingoController implements NumeroApi {

    private final NumeroBingoService numeroBingoService;

    private final NumeroBingoMapper numeroBingoMapper;

    public NumeroBingoController(NumeroBingoService numeroBingoService, NumeroBingoMapper numeroBingoMapper) {
        this.numeroBingoService = numeroBingoService;
        this.numeroBingoMapper = numeroBingoMapper;
    }

    @Override
    public Mono<ResponseEntity<NumerosResponse>> listarNumero(String bingoId, ServerWebExchange exchange) {
        return numeroBingoService.listarNumero(bingoId)
                .map(numeroBingoMapper::dtoAModelo)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Object>> validarNumerosBingo(Mono<NumeroRequest> numeroRequest, ServerWebExchange exchange) {
        return numeroBingoService.validarNumerosBingo(numeroRequest.map(numeroBingoMapper::modeloADto))
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Object>> obtenerNumero(String bingoId, ServerWebExchange exchange) {
        return numeroBingoService.obtenerNumero(bingoId)
                .map(ResponseEntity::ok);
    }
}