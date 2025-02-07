package com.bingo.service.bingo;

import com.bingo.model.bingo.Bingo;
import com.bingo.repository.bingo.BingoRepository;
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

@ExtendWith(MockitoExtension.class)
class BingoServiceImplTest {

    @InjectMocks
    BingoServiceImpl bingoService;

    @Mock
    BingoRepository bingoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bingoService = new BingoServiceImpl(bingoRepository);
    }

    @Test
    void crearBingo(){
        Bingo bingo = Bingo.builder()
                .id("67a559cfdc5e325a849da854")
                .motivo("Pro salud")
                .fechaJuego("06/02/2025")
                .build();

        Mockito.when(bingoRepository.save(Mockito.any(Bingo.class))).thenReturn(Mono.just(bingo));

        Mono<String> resultado = bingoService.crearBingo(Mono.just(bingo));

        StepVerifier.create(resultado)
                .expectNext("Bingo creado.")
                .verifyComplete();

        Mockito.verify(bingoRepository).save(Mockito.any(Bingo.class));
    }

    @Test
    void listarBingo() {
        Bingo bingo = Bingo.builder()
                .id("67a559cfdc5e325a849da854")
                .motivo("Pro salud")
                .fechaJuego("06/02/2025")
                .build();

        Mockito.when(bingoRepository.findAll()).thenReturn(Flux.just(bingo));

        Flux<Bingo> resultado = bingoService.listarBingo();

        StepVerifier.create(resultado)
                .expectNext(bingo)
                .verifyComplete();

        Mockito.verify(bingoRepository).findAll();
    }
}