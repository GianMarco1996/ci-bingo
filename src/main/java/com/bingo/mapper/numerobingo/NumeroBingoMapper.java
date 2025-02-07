package com.bingo.mapper.numerobingo;

import com.bingo.model.NumeroRequest;
import com.bingo.model.NumerosResponse;
import com.bingo.model.numerobingo.NumeroBingoDTO;
import com.bingo.model.numerobingo.NumeroBingoValidarDTO;
import org.springframework.stereotype.Component;

@Component
public class NumeroBingoMapper {

    public NumerosResponse dtoAModelo(NumeroBingoDTO dto) {
        return new NumerosResponse().numeros(dto.getNumeros());
    }

    public NumeroBingoValidarDTO modeloADto(NumeroRequest modelo) {
        return NumeroBingoValidarDTO.builder()
                .numeros(modelo.getNumeros())
                .cartonId(modelo.getCartonId())
                .build();
    }
}