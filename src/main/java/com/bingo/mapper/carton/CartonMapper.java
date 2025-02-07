package com.bingo.mapper.carton;

import com.bingo.model.*;
import com.bingo.model.carton.Carton;
import com.bingo.model.carton.CartonDTO;
import com.bingo.util.CartonUtil;
import org.springframework.stereotype.Component;

@Component
public class CartonMapper {

    public Carton modeloADocumento(CartonRequest modelo) {
        return Carton.builder()
                .nombre(modelo.getNombre())
                .bingoId(modelo.getBingoId())
                .build();
    }

    public CartonDTO documentoADto(Carton documento) {
        return CartonDTO.builder()
                .id(documento.getId())
                .nombre(documento.getNombre())
                .cartonLetras(CartonUtil.obtenerCartonLetra(documento.getNumeros()))
                .build();
    }

    public CartonResponse dtoAModelo(CartonDTO dto) {
        return new CartonResponse()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .carton(new CartonResponseCarton()
                        .b(dto.getCartonLetras().getLetraB())
                        .i(dto.getCartonLetras().getLetraI())
                        .n(dto.getCartonLetras().getLetraN())
                        .g(dto.getCartonLetras().getLetraG())
                        .o(dto.getCartonLetras().getLetraO()));
    }

}