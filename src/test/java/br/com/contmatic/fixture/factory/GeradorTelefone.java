package br.com.contmatic.fixture.factory;

import java.util.Random;

/**
 * The Class GeradorTelefone.
 */
public final class GeradorTelefone {

    /** The ddd random. */
    private static int dddRandom;

    private static int dddRandomInvalidos;

    private GeradorTelefone() {

    }

    /**
     * Gerador cell phone.
     *
     * @return the string
     */
    public static String geradorCellPhone() {
        StringBuilder cellPhoneRandom = new StringBuilder();
        cellPhoneRandom = new StringBuilder();
        cellPhoneRandom.append(dddRandom).append(9).append(new Random().nextInt(88888888) + 11111111);
        return cellPhoneRandom.toString();
    }

    public static String geradorPhoneOrCellPhoneInvalido() {
        StringBuilder cellPhoneRandom = new StringBuilder();
        cellPhoneRandom = new StringBuilder();
        cellPhoneRandom.append(dddRandomInvalidos).append(9).append(new Random().nextInt(888888) + 111111);
        return cellPhoneRandom.toString();
    }

    /**
     * Gerador phone.
     *
     * @return the string
     */
    public static String geradorPhone() {
        StringBuilder phoneRandom = new StringBuilder();
        phoneRandom = new StringBuilder();
        phoneRandom.append(dddRandom).append(new Random().nextInt(88888888) + 11111111);
        return phoneRandom.toString();
    }


}
