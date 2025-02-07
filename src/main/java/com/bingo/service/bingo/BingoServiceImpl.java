package com.bingo.service.bingo;

import com.bingo.model.bingo.Bingo;
import com.bingo.repository.bingo.BingoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BingoServiceImpl implements BingoService {

    private final BingoRepository bingoRepository;

    public BingoServiceImpl(BingoRepository bingoRepository) {
        this.bingoRepository = bingoRepository;
    }

    @Override
    public Mono<String> crearBingo(Mono<Bingo> bingo) {
        return bingo.flatMap(bingoRepository::save)
                .map(b -> "Bingo creado.");
    }

    @Override
    public Flux<Bingo> listarBingo() {
        return bingoRepository.findAll();
    }
}
