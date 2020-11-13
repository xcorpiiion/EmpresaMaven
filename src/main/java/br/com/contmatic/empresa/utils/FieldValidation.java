package br.com.contmatic.empresa.utils;

import org.joda.time.DateTime;

import static br.com.contmatic.constantes.Constante.DATA_CRIACAO_EMPRESA;
import static br.com.contmatic.constantes.Constante.DATA_NASCIMENTO;
import static com.google.common.base.Preconditions.checkArgument;

public final class FieldValidation {

    private FieldValidation() {
        super();
    }

    public static void isDataGreaterThanCurrent(DateTime yourDate, String errorMenssage) {
        checkArgument(yourDate.isBefore(new DateTime().getMillis()), errorMenssage);
    }

    public static void isLessThanCreateDate(DateTime createDate, DateTime yourDate, String errorMenssage) {
        checkArgument(createDate.isBefore(yourDate.getMillis()), errorMenssage);
    }

    public static void isDataLessThan1998(DateTime yourDate, String errorMenssage) {
        checkArgument(yourDate.isAfter(DATA_CRIACAO_EMPRESA), errorMenssage);
    }

    public static void isDataLessThan1920(DateTime yourDate, String errorMenssage) {
        checkArgument(DATA_NASCIMENTO.isBefore(yourDate), errorMenssage);
    }

}
