package com.bingo.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class NumeroBingoUtil {

    private NumeroBingoUtil() {}

    public static Integer generarNumero() {
        Random random = new Random();
        return random.nextInt(75) + 1;
    }

    public static String convertirListadoAString(List<Integer> numeros) {
        return numeros.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    public static String validarNumeros(String numerosBingo, List<Integer> numerosCarton) {
        String respuesta;
        List<Integer> listaNumeros = Arrays.stream(numerosBingo.split(",\\s*"))
                .map(Integer::parseInt)
                .toList();

        boolean validar = numerosCarton.stream().allMatch(listaNumeros::contains);
        if (validar) {
            respuesta = "Carton validado - es el ganador.";
        } else {
            respuesta = "Carton validado - No hay ganador, sigan jugando.";
        }
        return respuesta;
    }
}