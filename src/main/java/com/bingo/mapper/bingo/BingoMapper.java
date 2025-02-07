package com.bingo.mapper.bingo;

import com.bingo.model.BingoRequest;
import com.bingo.model.BingoResponse;
import com.bingo.model.bingo.Bingo;
import org.springframework.stereotype.Component;

@Component
public class BingoMapper {

    public Bingo modeloADocumento(BingoRequest modelo) {
        return Bingo.builder()
                .motivo(modelo.getMotivo())
                .fechaJuego(modelo.getFechaJuego())
                .build();
    }

    public BingoResponse documentoAModelo(Bingo documento) {
        return new BingoResponse()
                .id(documento.getId())
                .motivo(documento.getMotivo())
                .fechaJuego(documento.getFechaJuego());
    }
}