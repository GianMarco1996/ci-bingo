package com.bingo.service.carton;

import com.bingo.mapper.carton.CartonMapper;
import com.bingo.model.carton.Carton;
import com.bingo.model.carton.CartonDTO;
import com.bingo.repository.carton.CartonRepository;
import com.bingo.util.CartonUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Esta clase {@link CartonServiceImpl} implementa la interface {@link CartonService} que contiene los métodos
 * para generar y obtener los cartones.
 *
 * @see CartonService
 * @author Gian Castro
 * @version 1.0
 */
@Service
public class CartonServiceImpl implements CartonService {

    /**
     * Se realiza una inyección de dependencia.
     */
    private final CartonRepository cartonRepository;

    /**
     * Se realiza una inyección de dependencia.
     */
    private final CartonMapper cartonMapper;

    /**
     * Constructor que recibe las instancias de {@link CartonRepository} y {@link CartonMapper} se conoce
     * como inyección de dependencia por constructores.
     */
    public CartonServiceImpl(CartonRepository cartonRepository, CartonMapper cartonMapper) {
        this.cartonRepository = cartonRepository;
        this.cartonMapper = cartonMapper;
    }

    /**
     * Este método genera un carton del bingo.
     *
     * @param carton Representa el objeto carton con los datos necesarios para generarlo.
     * @return Devuelve una cadena con el mensaje de creación.
     */
    @Override
    public Mono<String> generarCarton(Mono<Carton> carton) {
        return carton.flatMap(c -> cartonRepository.save(Carton.builder()
                        .nombre(c.getNombre())
                        .bingoId(c.getBingoId())
                        .numeros(CartonUtil.generarNumerosCarton())
                        .build()))
                .map(c -> "Carton generado correctamente.");
    }

    /**
     * Este método obtiene los cartones.
     *
     * @return Devuelve los cartones generados.
     */
    @Override
    public Flux<CartonDTO> obtenerCarton() {
        return cartonRepository.findAll()
                .map(cartonMapper::documentoADto);
    }
}