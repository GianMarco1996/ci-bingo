package com.bingo.service.numerobingo;

import com.bingo.model.numerobingo.NumeroBingo;
import com.bingo.model.numerobingo.NumeroBingoDTO;
import com.bingo.model.numerobingo.NumeroBingoValidarDTO;
import com.bingo.repository.carton.CartonRepository;
import com.bingo.repository.numerobingo.NumeroBingoRepository;
import com.bingo.util.NumeroBingoUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Esta clase {@link NumeroBingoServiceImpl} implementa la interface {@link NumeroBingoService} que contiene los métodos
 * para obtener, listar validar los números del bingo.
 *
 * @see NumeroBingoService
 * @author Gian Castro
 * @version 1.0
 */
@Service
public class NumeroBingoServiceImpl implements NumeroBingoService {

    /**
     * Se realiza una inyección de dependencia.
     */
    private final NumeroBingoRepository numeroBingoRepository;

    /**
     * Se realiza una inyección de dependencia.
     */
    private final CartonRepository cartonRepository;

    /**
     * Constructor que recibe las instancias de {@link NumeroBingoRepository} y {@link CartonRepository} se conoce
     * como inyección de dependencia por constructores.
     */
    public NumeroBingoServiceImpl(NumeroBingoRepository numeroBingoRepository, CartonRepository cartonRepository) {
        this.numeroBingoRepository = numeroBingoRepository;
        this.cartonRepository = cartonRepository;
    }

    /**
     * Este método obtiene un numero aleatorio para el juego del bingo.
     *
     * @param bingoId Representa el ID del bingo.
     * @return Devuelve un entero con el número obtenido.
     */
    @Override
    public Mono<Integer> obtenerNumero(String bingoId) {
        return numeroBingoRepository.findNumeroBingoByBingoId(bingoId)
                .switchIfEmpty(numeroBingoRepository.save(NumeroBingo.builder()
                        .bingoId(bingoId)
                        .numeros(List.of(NumeroBingoUtil.generarNumero()))
                        .build()))
                .flatMap(numeroBingo -> {
                    Integer numeroRandom = NumeroBingoUtil.generarNumero();
                    if (numeroBingo.getNumeros().contains(numeroRandom)) {
                        return obtenerNumero(bingoId);
                    } else {
                        numeroBingo.getNumeros().add(numeroRandom);
                        return numeroBingoRepository.save(numeroBingo)
                                .map(savedNumeroBingo -> numeroRandom);
                    }
                });
    }

    /**
     * Este método lista los números aleatorios obtenidos para el juego del bingo.
     *
     * @param bingoId Representa el ID del bingo.
     * @return Devuelve los números obtenidos.
     */
    @Override
    public Mono<NumeroBingoDTO> listarNumero(String bingoId) {
        return numeroBingoRepository.findNumeroBingoByBingoId(bingoId)
                .map(numeroBingo -> NumeroBingoDTO.builder()
                        .numeros(NumeroBingoUtil.convertirListadoAString(numeroBingo.getNumeros()))
                        .build());
    }

    /**
     * Este método valida los números obtenidos con la cartilla del jugador y
     * verificar si gano el juego o no.
     *
     * @param numeroBingoValidar Representa el objeto con los datos necesarios para realizar la validación.
     * @return Devuelve una cadena con el resultado de la validación.
     */
    @Override
    public Mono<String> validarNumerosBingo(Mono<NumeroBingoValidarDTO> numeroBingoValidar) {
        return numeroBingoValidar.flatMap(
                nbv -> cartonRepository.findById(nbv.getCartonId())
                        .map(carton -> NumeroBingoUtil.validarNumeros(nbv.getNumeros(), carton.getNumeros())));
    }
}