package com.bingo.model.numerobingo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "numerosBingo")
public class NumeroBingo {

    @Id
    private String id;

    private String bingoId;

    private List<Integer> numeros;
}