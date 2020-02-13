package br.com.contmatic.fixture.factory;

import java.util.Random;

import br.com.contmatic.constantes.Constante;

/**
 * The Class GeradorTelefone.
 */
public class GeradorTelefone {

    /** The ddd random. */
    private static int dddRandom;

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
    
    /**
     * Gerador phone.
     *
     * @return the string
     */
    public static String geradorPhone() {
        StringBuilder cellPhoneRandom = new StringBuilder();
        while (!cellPhoneRandom.toString().matches(Constante.PHONE_VALIDATION)) {
            geradorDDD();
            cellPhoneRandom = new StringBuilder();
            cellPhoneRandom.append(dddRandom).append(new Random().nextInt(88888888) + 11111111);
        }
        return cellPhoneRandom.toString();
    }

    /**
     * Gerador DDD.
     */
    private static void geradorDDD() {
        dddRandom = new Random().nextInt(88) + 11;
    }

}
