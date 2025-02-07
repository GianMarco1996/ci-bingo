package com.bingo.service.numerobingo;

import com.bingo.model.carton.Carton;
import com.bingo.model.numerobingo.NumeroBingo;
import com.bingo.model.numerobingo.NumeroBingoDTO;
import com.bingo.model.numerobingo.NumeroBingoValidarDTO;
import com.bingo.repository.carton.CartonRepository;
import com.bingo.repository.numerobingo.NumeroBingoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class NumeroBingoServiceImplTest {

    @InjectMocks
    NumeroBingoServiceImpl numeroBingoService;

    @Mock
    NumeroBingoRepository numeroBingoRepository;

    @Mock
    CartonRepository cartonRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        numeroBingoService = new NumeroBingoServiceImpl(numeroBingoRepository, cartonRepository);
    }

    @Test
    void listarNumero() {
        String bingoId = "67a559cfdc5e325a849da853";

        NumeroBingo numeroBingo = NumeroBingo.builder()
                .id("67a559cfdc5e325a849da854")
                .bingoId(bingoId)
                .numeros(Arrays.asList(3, 8, 26, 45, 7, 1))
                .build();

        NumeroBingoDTO numeroBingoDTO = NumeroBingoDTO.builder()
                .numeros("3, 8, 26, 45, 7, 1")
                .build();

        Mockito.when(numeroBingoRepository.findNumeroBingoByBingoId(Mockito.anyString()))
                .thenReturn(Mono.just(numeroBingo));

        Mono<NumeroBingoDTO> resultado = numeroBingoService.listarNumero(bingoId);

        StepVerifier.create(resultado)
                .expectNext(numeroBingoDTO)
                .verifyComplete();
    }

    @Test
    void validarNumerosBingo() {
        NumeroBingoValidarDTO numeroBingo = NumeroBingoValidarDTO.builder()
                .numeros("3, 8, 26, 45, 7, 1")
                .cartonId("67a559cfdc5e325a849da854")
                .build();

        Carton carton = Carton.builder()
                .id("67a559cfdc5e325a849da854")
                .nombre("Gian Marco")
                .bingoId("67a559cfdc5e325a849da853")
                .numeros(Arrays.asList(3, 8, 26, 45, 7, 1))
                .build();

        Mockito.when(cartonRepository.findById(Mockito.anyString()))
                .thenReturn(Mono.just(carton));

        Mono<String> resultado = numeroBingoService.validarNumerosBingo(Mono.just(numeroBingo));

        StepVerifier.create(resultado)
                .expectNext("Cartón validado - es el ganador.")
                .verifyComplete();
    }
}