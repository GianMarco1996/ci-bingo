package com.bingo.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Esta clase {@link NumeroBingoUtil} contiene métodos utilitarios que ayudan al desarrollo.
 *
 * @author Gian Castro
 * @version 1.0
 */
public class NumeroBingoUtil {

    /**
     * Constructor privado vacío.
     */
    private NumeroBingoUtil() {}

    /**
     * Este método genera un número aleatorio para el juego del bingo.
     *
     * @return Devuelve un entero con el valor del número indicado.
     */
    public static Integer generarNumero() {
        Random random = new Random();
        return random.nextInt(75) + 1;
    }

    /**
     * Este método convierte a cadena un listado de números.
     *
     * @param numeros Representa el listado de números.
     * @return Devuelve una cadena con los números.
     */
    public static String convertirListadoAString(List<Integer> numeros) {
        return numeros.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    /**
     * Este método se encarga de validar los números.
     *
     * @param numerosBingo Representa una cadena con todos los números del bingo que ya salieron en el juego.
     * @param numerosCarton Representa un listado con los números del carton de la persona que grito bingo.
     * @return Devuelve una cadena con la validación.
     */
    public static String validarNumeros(String numerosBingo, List<Integer> numerosCarton) {
        List<Integer> listaNumeros = Arrays.stream(numerosBingo.split(",\\s*"))
                .map(Integer::parseInt)
                .toList();

        boolean validar = numerosCarton.stream().allMatch(listaNumeros::contains);

        return validar ? "Cartón validado - es el ganador." : "Cartón validado - No hay ganador, sigan jugando.";
    }
}