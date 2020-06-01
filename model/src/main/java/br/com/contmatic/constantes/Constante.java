package br.com.contmatic.constantes;
/**
 * The Class Constante.
 */
public final class Constante {

    /** The Constant ILLEGAL_WORD. */
    public static final String ILLEGAL_WORD = "^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$";

    /** The Constant ILLEGAL_NUMBER. */
    public static final String ILLEGAL_NUMBER = "^[0-9]*$";

    /** The Constant VALIDATION_EMAIL. */
    public static final String VALIDATION_EMAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    /** The Constant PHONE_VALIDATION. */
    public static final String PHONE_VALIDATION = "(([0-9]{8})|([0-9]{9}))";

    /**
     * Instantiates a new constante.
     */
    private Constante() {
    }

}
