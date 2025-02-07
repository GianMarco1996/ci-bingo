package com.bingo.controller.carton;

import com.bingo.mapper.carton.CartonMapper;
import com.bingo.model.CartonRequest;
import com.bingo.model.CartonResponse;
import com.bingo.model.carton.CartonDTO;
import com.bingo.model.carton.CartonLetraDTO;
import com.bingo.service.carton.CartonService;
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
class CartonControllerTest {

    @InjectMocks
    CartonController controller;

    @Mock
    CartonService cartonService;

    @Mock
    CartonMapper cartonMapper;

    @Mock
    private ServerWebExchange exchange;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new CartonController(cartonService, cartonMapper);
    }

    @Test
    void generarCarton() {
        CartonRequest cartonRequest = new CartonRequest()
                .nombre("Gian Marco")
                .bingoId("67a559cfdc5e325a849da854");

        Mockito.when(cartonService.generarCarton(Mockito.any(Mono.class)))
                .thenReturn(Mono.just("Carton generado correctamente."));

        Mono<ResponseEntity<Object>> resultado = controller.generarCarton(Mono.just(cartonRequest), exchange);

        StepVerifier.create(resultado)
                .consumeNextWith(responseEntity -> {
                    Assertions.assertNotNull(responseEntity);
                    Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
                })
                .verifyComplete();
    }

    @Test
    void obtenerCarton() {
        CartonDTO carton = CartonDTO.builder()
                .id("67a559cfdc5e325a849da853")
                .nombre("Gian Marco")
                .bingoId("67a559cfdc5e325a849da854")
                .cartonLetras(CartonLetraDTO.builder()
                        .letraB("1, 3, 5, 11, 15")
                        .letraI("18, 23, 24, 26, 29")
                        .letraN("32, 35, 36, 44")
                        .letraG("47, 49, 50, 55, 56")
                        .letraO("61, 62, 69, 74, 75")
                        .build())
                .build();

        Mockito.when(cartonService.obtenerCarton()).thenReturn(Flux.just(carton));

        Mono<ResponseEntity<Flux<CartonResponse>>> resultado = controller.obtenerCarton(exchange);

        StepVerifier.create(resultado)
                .consumeNextWith(responseEntity -> {
                    Assertions.assertNotNull(responseEntity);
                    Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
                })
                .verifyComplete();
    }
}