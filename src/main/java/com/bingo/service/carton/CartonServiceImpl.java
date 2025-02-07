package com.bingo.service.carton;

import com.bingo.mapper.carton.CartonMapper;
import com.bingo.model.carton.Carton;
import com.bingo.model.carton.CartonDTO;
import com.bingo.repository.carton.CartonRepository;
import com.bingo.util.CartonUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CartonServiceImpl implements CartonService {

    private final CartonRepository cartonRepository;

    private final CartonMapper cartonMapper;

    public CartonServiceImpl(CartonRepository cartonRepository, CartonMapper cartonMapper) {
        this.cartonRepository = cartonRepository;
        this.cartonMapper = cartonMapper;
    }

    @Override
    public Mono<String> generarCarton(Mono<Carton> carton) {
        return carton.flatMap(c -> cartonRepository.save(Carton.builder()
                        .nombre(c.getNombre())
                        .bingoId(c.getBingoId())
                        .numeros(CartonUtil.generarNumerosCarton())
                        .build()))
                .map(c -> "Carton generado correctamente.");
    }

    @Override
    public Flux<CartonDTO> obtenerCarton() {
        return cartonRepository.findAll()
                .map(cartonMapper::documentoADto);
    }
}