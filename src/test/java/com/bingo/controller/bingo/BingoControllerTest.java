package com.bingo.controller.bingo;

import com.bingo.mapper.bingo.BingoMapper;
import com.bingo.model.BingoRequest;
import com.bingo.model.BingoResponse;
import com.bingo.model.bingo.Bingo;
import com.bingo.service.bingo.BingoService;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class BingoControllerTest {

    @InjectMocks
    BingoController controller;

    @Mock
    BingoService bingoService;

    @Mock
    BingoMapper bingoMapper;

    @Mock
    private ServerWebExchange exchange;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new BingoController(bingoService, bingoMapper);
    }

    @Test
    void crearBingo() {
        BingoRequest bingoRequest = new BingoRequest()
                .motivo("Pro salud")
                .fechaJuego("06/02/2025");

        Mockito.when(bingoService.crearBingo(Mockito.any(Mono.class))).thenReturn(Mono.just("Bingo creado."));

        Mono<ResponseEntity<Object>> resultado = controller.crearBingo(Mono.just(bingoRequest), exchange);

        StepVerifier.create(resultado)
                .consumeNextWith(responseEntity -> {
                    Assertions.assertNotNull(responseEntity);
                    Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
                })
                .verifyComplete();

    }

    @Test
    void listarBingo() {
        Bingo bingo = Bingo.builder()
                .id("67a559cfdc5e325a849da854")
                .motivo("Pro salud")
                .fechaJuego("06/02/2025")
                .build();

        Mockito.when(bingoService.listarBingo()).thenReturn(Flux.just(bingo));

        Mono<ResponseEntity<Flux<BingoResponse>>> resultado = controller.listarBingo(exchange);

        StepVerifier.create(resultado)
                .consumeNextWith(responseEntity -> {
                    Assertions.assertNotNull(responseEntity);
                    Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
                })
                .verifyComplete();
    }
}