package br.com.contmatic.fixture.factory;

import java.util.Random;

import br.com.contmatic.constantes.Constante;

public class GeradorTelefone {

    private static int dddRandom;

    public static String geradorCellPhone() {
        StringBuilder cellPhoneRandom = new StringBuilder();
        while (!cellPhoneRandom.toString().matches(Constante.PHONE_VALIDATION)) {
            geradorDDD();
            cellPhoneRandom = new StringBuilder();
            cellPhoneRandom.append(dddRandom).append(9).append(new Random().nextInt(88888888) + 11111111);
        }
        return cellPhoneRandom.toString();
    }
    
    public static String geradorPhone() {
        StringBuilder cellPhoneRandom = new StringBuilder();
        while (!cellPhoneRandom.toString().matches(Constante.PHONE_VALIDATION)) {
            geradorDDD();
            cellPhoneRandom = new StringBuilder();
            cellPhoneRandom.append(dddRandom).append(new Random().nextInt(88888888) + 11111111);
        }
        return cellPhoneRandom.toString();
    }

    private static void geradorDDD() {
        dddRandom = new Random().nextInt(88) + 11;
    }

}
