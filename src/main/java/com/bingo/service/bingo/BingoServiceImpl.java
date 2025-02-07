package com.bingo.service.bingo;

import com.bingo.model.bingo.Bingo;
import com.bingo.repository.bingo.BingoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Esta clase {@link BingoServiceImpl} implementa la interface {@link BingoService} que contiene los métodos
 * para crear y listar bingos.
 *
 * @see BingoService
 * @author Gian Castro
 * @version 1.0
 */
@Service
public class BingoServiceImpl implements BingoService {

    /**
     * Se realiza una inyección de dependencia.
     */
    private final BingoRepository bingoRepository;

    /**
     * Constructor que recibe la instancia de {@link BingoRepository} se conoce
     * como inyección de dependencia por constructores.
     */
    public BingoServiceImpl(BingoRepository bingoRepository) {
        this.bingoRepository = bingoRepository;
    }

    /**
     * Este método crea un bingo.
     *
     * @param bingo Representa el objeto bingo con los datos necesarios para crear.
     * @return Devuelve una cadena con el mensaje de creación.
     */
    @Override
    public Mono<String> crearBingo(Mono<Bingo> bingo) {
        return bingo.flatMap(bingoRepository::save)
                .map(b -> "Bingo creado.");
    }

    /**
     * Este método lista los bingos.
     *
     * @return Devuelve la lista de bingos creados.
     */
    @Override
    public Flux<Bingo> listarBingo() {
        return bingoRepository.findAll();
    }
}
