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

@Service
public class NumeroBingoServiceImpl implements NumeroBingoService {

    private final NumeroBingoRepository numeroBingoRepository;

    private final CartonRepository cartonRepository;

    public NumeroBingoServiceImpl(NumeroBingoRepository numeroBingoRepository, CartonRepository cartonRepository) {
        this.numeroBingoRepository = numeroBingoRepository;
        this.cartonRepository = cartonRepository;
    }

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

    @Override
    public Mono<NumeroBingoDTO> listarNumero(String bingoId) {
        return numeroBingoRepository.findNumeroBingoByBingoId(bingoId)
                .map(numeroBingo -> NumeroBingoDTO.builder()
                        .numeros(NumeroBingoUtil.convertirListadoAString(numeroBingo.getNumeros()))
                        .build());
    }

    @Override
    public Mono<String> validarNumerosBingo(Mono<NumeroBingoValidarDTO> numeroBingoValidar) {
        return numeroBingoValidar.flatMap(
                nbv -> cartonRepository.findById(nbv.getCartonId())
                        .map(carton -> NumeroBingoUtil.validarNumeros(nbv.getNumeros(), carton.getNumeros())));
    }
}