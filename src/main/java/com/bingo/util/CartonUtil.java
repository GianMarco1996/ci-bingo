package com.bingo.util;

import com.bingo.model.carton.CartonLetraDTO;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Esta clase {@link CartonUtil} contiene métodos utilitarios que ayudan al desarrollo.
 *
 * @author Gian Castro
 * @version 1.0
 */
public class CartonUtil {

    /**
     * Constructor privado vacío.
     */
    private CartonUtil() {}

    /**
     * Este método indica como generar los números aleatorios para la creación del carton, a través del
     * método generarNumeros estos números están en el rango de 1 al 75. Y también se indica un rango de
     * números por cada letra del bingo, por ejemplo:
     * B: que comprende del 1 al 15 y debe contener solo 5 números por esta letra.
     * I: que comprende del 16 al 30 y debe contener solo 5 números por esta letra.
     * N: que comprende del 31 al 45 y debe contener solo 4 números por esta letra.
     * G: que comprende del 46 al 60 y debe contener solo 5 números por esta letra.
     * O: que comprende del 61 al 75 y debe contener solo 5 números por esta letra.
     *
     * @return Devuelve una lista de enteros con los números para el carton.
     */
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

    /**
     * Este método genera los números aleatorios.
     *
     * @param totalNumero Representa el total de números a generar.
     * @param maximo Representa el valor más alto del número, no debe generar un valor más alto del indicado.
     * @param aumento Representa el de cuanto va aumentar.
     *
     * @return Devuelve un Set que indica que solo va aceptar valores unicos.
     */
    private static Set<Integer> generarNumeros(int totalNumero, int maximo, int aumento) {
        Set<Integer> numerosUnicos = new HashSet<>();
        Random random = new Random();

        while (numerosUnicos.size() < totalNumero) {
            int numero = random.nextInt(maximo - aumento + 1) + aumento;
            numerosUnicos.add(numero);
        }

        return numerosUnicos;
    }

    /**
     * Este método ordena los números en cada letra correspondiente que obtiene a través de un listado de enteros.
     *
     * @param numeros Representa el listado de enteros con los números de la cartilla.
     *
     * @return Devuelve un {@link CartonLetraDTO} con los números ordenados por letra.
     */
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