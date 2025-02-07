package com.bingo.model.carton;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "cartones")
public class Carton {

    @Id
    private String id;

    private String nombre;

    private String bingoId;

    private List<Integer> numeros;
}