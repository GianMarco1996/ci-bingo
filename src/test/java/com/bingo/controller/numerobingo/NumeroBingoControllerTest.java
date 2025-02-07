package com.bingo.controller.numerobingo;

import com.bingo.mapper.numerobingo.NumeroBingoMapper;
import com.bingo.model.NumeroRequest;
import com.bingo.model.NumerosResponse;
import com.bingo.model.numerobingo.NumeroBingoDTO;
import com.bingo.service.numerobingo.NumeroBingoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class NumeroBingoControllerTest {

    @InjectMocks
    NumeroBingoController controller;

    @Mock
    NumeroBingoService numeroBingoService;

    @Mock
    NumeroBingoMapper numeroBingoMapper;

    @Mock
    private ServerWebExchange exchange;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new NumeroBingoController(numeroBingoService, numeroBingoMapper);
    }

    @Test
    void listarNumero() {
        String bingoId = "67a559cfdc5e325a849da854";

        NumeroBingoDTO numeroBingo = NumeroBingoDTO.builder()
                .numeros("3, 5, 25, 36")
                .build();

        NumerosResponse numerosResponse = new NumerosResponse()
                .numeros("3, 5, 25, 36");

        Mockito.when(numeroBingoService.listarNumero(Mockito.anyString()))
                .thenReturn(Mono.just(numeroBingo));

        Mockito.when(numeroBingoMapper.dtoAModelo(Mockito.any(NumeroBingoDTO.class))).thenReturn(numerosResponse);

        Mono<ResponseEntity<NumerosResponse>> resultado = controller.listarNumero(bingoId, exchange);

        StepVerifier.create(resultado)
                .consumeNextWith(responseEntity -> {
                    Assertions.assertNotNull(responseEntity);
                    Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
                })
                .verifyComplete();
    }

    @Test
    void validarNumerosBingo() {
        NumeroRequest numeroRequest = new NumeroRequest()
                .numeros("3, 5, 25, 36")
                .cartonId("67a559cfdc5e325a849da854");

        Mockito.when(numeroBingoService.validarNumerosBingo(Mockito.any(Mono.class)))
                .thenReturn(Mono.just("Carton validado - es el ganador."));

        Mono<ResponseEntity<Object>> resultado = controller.validarNumerosBingo(Mono.just(numeroRequest), exchange);

        StepVerifier.create(resultado)
                .consumeNextWith(responseEntity -> {
                    Assertions.assertNotNull(responseEntity);
                    Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
                })
                .verifyComplete();
    }

    @Test
    void obtenerNumero() {
        String bingoId = "67a559cfdc5e325a849da854";

        Integer numeroAleatorio = 10;

        Mockito.when(numeroBingoService.obtenerNumero(Mockito.anyString())).thenReturn(Mono.just(numeroAleatorio));

        Mono<ResponseEntity<Object>> resultado = controller.obtenerNumero(bingoId, exchange);

        StepVerifier.create(resultado)
                .consumeNextWith(responseEntity -> {
                    Assertions.assertNotNull(responseEntity);
                    Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
                })
                .verifyComplete();
    }
}