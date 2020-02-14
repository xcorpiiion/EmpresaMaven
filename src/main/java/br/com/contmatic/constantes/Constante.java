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

    /** The Constant VALOR_ESTA_NULLO. */
    public static final String VALOR_ESTA_NULLO = "O valor esta nullo";

    /** The Constant VALOR_ESTA_VAZIO. */
    public static final String VALOR_ESTA_VAZIO = "O valor esta vazio";

    /** The Constant VALOR_NAO_E_VALIDO. */
    public static final String VALOR_NAO_E_VALIDO = "O valor não é valido";

    /** The Constant PHONE_VALIDATION. */
    public static final String PHONE_VALIDATION = "(([1][1-9])|([2][1-2])|([2][4-4])|([2][7-8])|([3][1-5])|([3][7-8])|([4][1-9])|([5][1])|([5][3-5])|([6][1-9])|([7][1])|([7][3-5])|([7][7])|([7][9])|([8][1-9]))(([0-9]{8})|([0-9]{9}))";

    /** The Constant NOME_E_MUITO_PEQUENO. */
    public static final String NOME_E_MUITO_PEQUENO = "O nome é muito pequeno";

    /** The Constant NOME_E_MUITO_GRANDE. */
    public static final String NOME_E_MUITO_GRANDE = "O nome é muito grande";

    /** The Constant PRECISA_SER_UM_VALOR_MAIOR. */
    public static final String PRECISA_SER_UM_VALOR_MAIOR = "O precisa ser um valor maior";

    /**
     * Instantiates a new constante.
     */
    private Constante() {
    }

}
