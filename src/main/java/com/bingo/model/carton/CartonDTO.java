package com.bingo.model.carton;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartonDTO {

    private String id;

    private String nombre;

    private String bingoId;

    private CartonLetraDTO cartonLetras;
}