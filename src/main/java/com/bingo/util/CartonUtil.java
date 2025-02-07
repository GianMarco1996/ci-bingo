package com.bingo.util;

import com.bingo.model.carton.CartonLetraDTO;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class CartonUtil {

    private CartonUtil() {}

    public static List<Integer> generarNumerosCarton() {
        List<int[]> parametros = Arrays.asList(
                new int[]{5, 15, 1},
                new int[]{5, 30, 16},
                new int[]{4, 45, 31},
                new int[]{5, 60, 46},
                new int[]{5, 75, 61}
        );

        return parametros.stream()
                .flatMap(param -> generarNumeros(param[0], param[1], param[2]).stream())
                .sorted()
                .toList();
    }

    private static Set<Integer> generarNumeros(int totalNumero, int maximo, int aumento) {
        Set<Integer> numerosUnicos = new HashSet<>();
        Random random = new Random();

        while (numerosUnicos.size() < totalNumero) {
            int numero = random.nextInt(maximo - aumento + 1) + aumento;
            numerosUnicos.add(numero);
        }

        return numerosUnicos;
    }

    public static CartonLetraDTO obtenerCartonLetra(List<Integer> numeros) {
        Map<String, int[]> rangos = Map.of(
                "B", new int[]{1, 15},
                "I", new int[]{16, 30},
                "N", new int[]{31, 45},
                "G", new int[]{46, 60},
                "O", new int[]{61, 75}
        );

        Map<String, List<Integer>> cartonMap = rangos.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> numeros.stream()
                                .filter(nu -> nu >= entry.getValue()[0] && nu <= entry.getValue()[1])
                                .toList()
                ));

        return CartonLetraDTO.builder()
                .letraB(cartonMap.get("B").toString())
                .letraI(cartonMap.get("I").toString())
                .letraN(cartonMap.get("N").toString())
                .letraG(cartonMap.get("G").toString())
                .letraO(cartonMap.get("O").toString())
                .build();
    }
}