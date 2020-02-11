package br.com.contmatic.constantes;

public final class Constante {

    public static final String ILLEGAL_WORD = "^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$";

    public static final String ILLEGAL_NUMBER = "^[0-9]*$";

    public static final String VALIDATION_EMAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    public static final String APENAS_REPOSITORES_PODEM_ALTERAR_OS_DADOS_DO_PRODUTO = "Apenas repositores podem alterar os dados do produto";

    public static final String NOME_NAO_PODE_ESTA_VAZIO = "Nome não pode esta vazio";

    public static final String EMAIL_INVALIDO = "Email invalido";

    public static final String EMAIL_NAO_PODE_ESTA_VAZIO = "Email não pode esta vazio";

    public static final String DATA_DE_NASCIMENTO_NAO_PODE_SER_NULLA = "Data de nascimento não pode ser nulla";

    public static final String O_ENDERECO_ESTA_NULL = "O endereço esta null";

    public static final String O_CPF_ESTA_VAZIO = "O cpf está vazio";

    public static final String O_CPF_ESTA_COM_UM_ESPACO_EM_BRANCO = "O cpf está com um espaço em branco";

    public static final String O_CPF_ESTA_INVALIDO = "O cpf está invalido";

    public static final String O_VALOR_ESTA_NULLO = "O valor esta nullo";

    public static final String O_VALOR_NAO_E_VALIDO = "O valor não é valido";

    public static final String O_TELEFONE_NAO_PODE_FICAR_VAZIO = "O telefone não pode ficar vazio";

    public static final String PHONE_VALIDATION = "(([1][1-9])|([2][1-2])|([2][4-8])|([3][1-5])|([3][7-8])|([4][1-9])|([5][1])|([5][3-5])|([6][1-9])|([7][1])|([7][3-5])|([7][7])|([7][9])|([8][1-9]))(([0-9]{8})|([0-9]{9}))";

    public static final String O_TELEFONE_NAO_E_VALIDO = "O telefone não é valido";
    
    private Constante() {
    }

}
