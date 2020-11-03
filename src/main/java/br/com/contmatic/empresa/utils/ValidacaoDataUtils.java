package br.com.contmatic.empresa.utils;

import org.joda.time.DateTime;

public final class ValidacaoDataUtils {

    private ValidacaoDataUtils() {

    }

    public static void isDataGreaterThanCurrent(DateTime yourDate, String errorMenssage) {
        if (yourDate.isAfter(new DateTime().getMillis())) {
            illegalState(errorMenssage);
        }
    }

    public static void isLessThanCreateDate(DateTime createDate, DateTime yourDate, String errorMenssage) {
        if (yourDate.isBefore(createDate.getMillis())) {
            illegalState(errorMenssage);
        }
    }

    public static void isDataLessThan1998(DateTime yourDate, String errorMenssage) {
        DateTime date = new DateTime(1998, 07, 01, 0, 0);
        if (yourDate.isBefore(date.getMillis())) {
            illegalState(errorMenssage);
        }
    }

    public static void isDataLessThan1920(DateTime yourDate, String errorMenssage) {
        DateTime date = new DateTime(1920, 01, 01, 0, 0);
        if (yourDate.isBefore(date.getMillis())) {
            illegalState(errorMenssage);
        }
    }

    public static void isGreaterThanEmpresaCreateDate(DateTime dataSaida, DateTime dataEntrada) {
        if (dataSaida.isBefore(dataEntrada.getMillis())) {
            throw new IllegalStateException("A data de saida da empresa precisa ser maior do que a data de entrada");
        }
    }

    private static void illegalState(String message) {
        throw new IllegalStateException(message);
    }

}
