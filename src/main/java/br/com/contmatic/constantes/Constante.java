package br.com.contmatic.constantes;

public final class Constante {

    public static final String ILLEGAL_WORD = "^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$";

    public static final String ILLEGAL_NUMBER = "^[0-9]*$";

    public static final String VALIDATION_EMAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    public static final String APENAS_REPOSITORES_PODEM_ALTERAR_OS_DADOS_DO_PRODUTO = "Apenas repositores podem alterar os dados do produto";
    
    private Constante() {
    }

}
