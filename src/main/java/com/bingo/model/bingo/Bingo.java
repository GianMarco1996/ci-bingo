package com.bingo.model.bingo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "bingos")
public class Bingo {

    @Id
    private String id;

    private String motivo;

    private String fechaJuego;
}