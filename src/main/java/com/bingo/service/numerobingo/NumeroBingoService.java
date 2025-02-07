package com.bingo.service.numerobingo;

import com.bingo.model.numerobingo.NumeroBingoDTO;
import com.bingo.model.numerobingo.NumeroBingoValidarDTO;
import reactor.core.publisher.Mono;

public interface NumeroBingoService {

    Mono<Integer> obtenerNumero(String bingoId);

    Mono<NumeroBingoDTO> listarNumero(String bingoId);

    Mono<String> validarNumerosBingo(Mono<NumeroBingoValidarDTO> numeroBingoValidar);
}