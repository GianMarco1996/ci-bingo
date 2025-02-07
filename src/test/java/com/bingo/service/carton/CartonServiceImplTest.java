package com.bingo.service.carton;

import com.bingo.mapper.carton.CartonMapper;
import com.bingo.model.carton.Carton;
import com.bingo.model.carton.CartonDTO;
import com.bingo.model.carton.CartonLetraDTO;
import com.bingo.repository.carton.CartonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class CartonServiceImplTest {

    @InjectMocks
    CartonServiceImpl cartonService;

    @Mock
    CartonRepository cartonRepository;

    @Mock
    CartonMapper cartonMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cartonService = new CartonServiceImpl(cartonRepository, cartonMapper);
    }

    @Test
    void generarCarton() {
        Carton carton = Carton.builder()
                .id("67a559cfdc5e325a849da853")
                .nombre("Gian Marco")
                .bingoId("67a559cfdc5e325a849da854")
                .numeros(Arrays.asList(1, 3, 5, 11, 15, 18, 23, 24, 26, 29, 32, 35, 36, 44, 47, 49, 50, 55, 56, 61, 62, 69, 74, 75))
                .build();

        Mockito.when(cartonRepository.save(Mockito.any(Carton.class))).thenReturn(Mono.just(carton));

        Mono<String> resultado = cartonService.generarCarton(Mono.just(carton));

        StepVerifier.create(resultado)
                .expectNext("Carton generado correctamente.")
                .verifyComplete();

        Mockito.verify(cartonRepository).save(Mockito.any(Carton.class));
    }

    @Test
    void obtenerCarton() {
        Carton carton = Carton.builder()
                .id("67a559cfdc5e325a849da853")
                .nombre("Gian Marco")
                .bingoId("67a559cfdc5e325a849da854")
                .numeros(Arrays.asList(1, 3, 5, 11, 15, 18, 23, 24, 26, 29, 32, 35, 36, 44, 47, 49, 50, 55, 56, 61, 62, 69, 74, 75))
                .build();

        CartonDTO cartonDTO = CartonDTO.builder()
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

        Mockito.when(cartonRepository.findAll()).thenReturn(Flux.just(carton));

        Mockito.when(cartonMapper.documentoADto(Mockito.any(Carton.class))).thenReturn(cartonDTO);

        Flux<CartonDTO> resultado = cartonService.obtenerCarton();

        StepVerifier.create(resultado)
                .expectNext(cartonDTO)
                .verifyComplete();

        Mockito.verify(cartonRepository).findAll();
    }
}