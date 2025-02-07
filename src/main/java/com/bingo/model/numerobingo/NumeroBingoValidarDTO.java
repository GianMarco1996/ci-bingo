package com.bingo.model.numerobingo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NumeroBingoValidarDTO {

    private String numeros;

    private String cartonId;
}