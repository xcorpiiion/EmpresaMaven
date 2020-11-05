package br.com.contmatic.telefone;

import br.com.contmatic.constantes.Constante;

import java.util.Random;

/**
 * The Class GeradorTelefone.
 */
public class GeradorTelefone {

    /** The ddd random. */
    private static int dddRandom;

    private static int dddRandomInvalidos;

    /**
     * Gerador cell phone.
     *
     * @return the string
     */
    public static String geradorCellPhone() {
        StringBuilder cellPhoneRandom = new StringBuilder();
        while (!cellPhoneRandom.toString().matches(Constante.PHONE_VALIDATION)) {
            geradorDDD();
            cellPhoneRandom = new StringBuilder();
            cellPhoneRandom.append(dddRandom).append(9).append(new Random().nextInt(88888888) + 11111111);
        }
        return cellPhoneRandom.toString();
    }

    public static String geradorCellPhoneInvalido() {
        StringBuilder cellPhoneRandom = new StringBuilder();
        geradorDDDInvalido();
        cellPhoneRandom = new StringBuilder();
        cellPhoneRandom.append(dddRandomInvalidos).append(9).append(new Random().nextInt(88888888) + 11111111);
        return cellPhoneRandom.toString();
    }

    /**
     * Gerador phone.
     *
     * @return the string
     */
    public static String geradorPhone() {
        StringBuilder phoneRandom = new StringBuilder();
        while (!phoneRandom.toString().matches(Constante.PHONE_VALIDATION)) {
            geradorDDD();
            phoneRandom = new StringBuilder();
            phoneRandom.append(dddRandom).append(new Random().nextInt(88888888) + 11111111);
        }
        return phoneRandom.toString();
    }

    public static String geradorPhoneInvalido() {
        StringBuilder phoneRandom = new StringBuilder();
        geradorDDDInvalido();
        phoneRandom = new StringBuilder();
        phoneRandom.append(dddRandomInvalidos).append(new Random().nextInt(88888888) + 11111111);

        return phoneRandom.toString();
    }

    /**
     * Gerador DDD.
     */
    private static void geradorDDD() {
        dddRandom = new Random().nextInt(88) + 11;
    }

    private static void geradorDDDInvalido() {
        int[] randomInvalideDDD = { 20, 23, 25, 26, 29, 30, 36, 29, 40, 50, 52, 56, 57, 58, 59, 60, 70, 72, 76, 78, 80, 90 };
        int index = new Random().nextInt(randomInvalideDDD.length);
        dddRandomInvalidos = randomInvalideDDD[index];
    }

}
